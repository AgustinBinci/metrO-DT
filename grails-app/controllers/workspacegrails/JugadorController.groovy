package workspacegrails
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

class JugadorController {

	def index() {
		def criteria = Jugador.createCriteria();
		def jugadores;
		List<JugadorIndex> jugadoresIndex = new ArrayList<>();

		if (params.sort && params.order) jugadores = criteria.list {
			and {
				order("posicion", "asc");
				order(params.sort, params.order);
			}
		}
		else jugadores = criteria.list() {
			order("posicion", "asc");
		}

		for (Jugador unJugador : jugadores) {
			//Jugador Index
			JugadorIndex unJugadorIndex = new JugadorIndex();
			unJugadorIndex.setId(unJugador.getId());
			unJugadorIndex.setNombre(unJugador.getNombre());
			unJugadorIndex.setPrecio(unJugador.getPrecio());
			unJugadorIndex.setPosicion(unJugador.getPosicion());
			
			//Club Index
			Club unClub = unJugador.getClub();
			if (unClub) {
				ClubIndex unClubIndex = new ClubIndex();
				unClubIndex.setId(unClub.getId());
				unClubIndex.setNombre(unClub.getNombre());
				unJugadorIndex.setClub(unClubIndex);
			}

			jugadoresIndex.add(unJugadorIndex);
		}

		return render(view:'index', model:[jugadores:jugadoresIndex]);
	}

	def create() {
		if (session.user && session.user.getAdmin()) {
			if (params.nombre && params.precio && params.idPosicion && params.idClub) {
				Posicion unaPosicion = Posicion.findById(params.idPosicion);
				if (unaPosicion) {
					Club unClub = Club.findById(params.idClub);
					if (unClub) {
						Jugador unJugador = new Jugador(nombre: params.nombre, precio: params.precio, posicion: unaPosicion, club: unClub);			
						if (unJugador.save()) flash.message = "Jugador creado!";
						else flash.errorMessage = "Error al crear Jugador";
						unClub.addToJugadores(unJugador);
						unClub.save();
						return redirect(controller:"club", action:"show", id: unClub.getId());
					}
					else {
						flash.errorMessage = "no hay club";
						return render(view:"create", model:[posiciones: Posicion.list(), idClub: params.idClub, clubes: Club.list()]);
					}
				}
				else {
					flash.errorMessage = "no hay posicion";
					return render(view:"create", model:[posiciones: Posicion.list(), idClub: params.idClub, clubes: Club.list()]);
				}
			}
			else {
				flash.errorMessage = "no hay parametros";
				return render(view:"create", model:[posiciones: Posicion.list(), idClub: params.idClub, clubes: Club.list()]);
			}
		}
		else {
			flash.errorMessage = "No tenes permisos para crear un jugador!";
			return render(view: "index");
		}
	}

	def show() {
		if (params.id) {
			Jugador unJugador = Jugador.findById(params.id);
			if (unJugador) {
				//Jugador Index
				JugadorIndex unJugadorIndex = new JugadorIndex();
				unJugadorIndex.setId(unJugador.getId());
				unJugadorIndex.setNombre(unJugador.getNombre());
				unJugadorIndex.setPrecio(unJugador.getPrecio());
				unJugadorIndex.setPosicion(unJugador.getPosicion());
			
				//Club Index
				Club unClub = unJugador.getClub();
				if (unClub) {
					ClubIndex unClubIndex = new ClubIndex();
					unClubIndex.setId(unClub.getId());
					unClubIndex.setNombre(unClub.getNombre());
					unJugadorIndex.setClub(unClubIndex);
				}
				return render(view:'show', model:[jugador:JugadorIndex]);
			}
			else {
				flash.errorMessage = "No existe ningun jugador con ese identificador";
				return redirect(action:"index");			
			}
		}
		else return render(view: "index");
	}

	def edit() {		
		if (session.user && session.user.getAdmin()) {
			if (params.id) {
				Jugador unJugador = Jugador.findById(params.id);
				if (unJugador) {
					if (params.nombre && params.precio && params.idPosicion && params.idClub) {
						Posicion unaPosicion = Posicion.findById(params.idPosicion);
						if (unaPosicion) {
							Club unClub = Club.findById(params.idClub);
							if (unClub) {
								if (unClub.getId() !=  unJugador.getClub().getId()) {
									unJugador.getClub().removeFromJugadores(unJugador);
									unClub.addToJugadores(unJugador);
									unJugador.getClub().save();
									unClub.save();
								}
								unJugador.setNombre(params.nombre);
								unJugador.setPrecio(Integer.parseInt(params.precio));
								unJugador.setPosicion(unaPosicion);
								unJugador.setClub(unClub);	
								if (unJugador.save()) flash.message = "Jugador editado correctamente";
								else flash.errorMessage = "Error al editar jugador";
								return redirect(controller: "club", action: "show", id: unClub.getId());
							}
							else {
								flash.errorMessage = "No existe ningun club con ese identificador";
								return render(view:"index");
							}
						}
						else {
							flash.errorMessage = "No existe ninguna posicion con ese identificador";
							return render(view:"index");
						}
					}
					else {

						return render(view: "edit", model:[jugador:unJugador, posiciones:Posicion.list(), clubes: Club.list()]);
					}
				}
				else return render(view:"index");
			}
			else return render(view:"index");
		}
		else {
			flash.errorMessage = "No tenes permisos para editar un jugador!";
			return render(view: "index");
		}
	}

	def delete() {
		if (session.user && session.user.getAdmin()) {
			if (params.id) {
				Jugador unJugador = Jugador.findById(params.id);
				if (unJugador) {

					//Borrar del club
					Club unClub = unJugador.getClub();
					unClub.removeFromJugadores(unJugador);
					unClub.save();
					unJugador.delete();
					flash.message = "Jugador eliminado correctamente";
					return redirect(controller: "club", action: "show", id: unClub.getId());
				}
				else flash.errorMessage = "No existe ningun jugador con ese identificador";
			}
			else return render(view:"index");
		}
		else {
			flash.errorMessage = "No tenes permisos para eliminar un jugador!";
			return render(view: "index");
		}
	}

	def showStatistics() {
		if (params.id) {
			Jugador unJugador = Jugador.findById(params.id);
			if (unJugador) {
				Iterator<DatosDeUnaFecha> it = unJugador.getDatosDelasFechas()iterator();
				List<DatosDeUnaFecha> datosDelasFechas = new ArrayList<DatosDeUnaFecha>();

				while (it.hasNext()) {
					datosDelasFechas.add(it.next());
				}

				Collections.sort(datosDelasFechas);

				return render(view: "showStatistics", model: [jugador: unJugador, datosDelasFechas: datosDelasFechas]);
			}
			else {
				flash.errorMessage = "No existe jugador con ese identificador";
				return render(view: "index");
			}
		}
		else {
			return render(view: "index");
		}
		return;
	}
 
}
