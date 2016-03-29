package workspacegrails

class DatosDeUnaFechaController {

	def index() {
		redirect(uri: "/");
	}	

	def create() {
		if (session.user && session.user.getAdmin()) {
			if (params.idJugador) {
				Jugador unJugador = Jugador.findById(params.idJugador);
				if (unJugador) {
					if (params.fecha && params.puntajeDelDiario && params.golesAfavor && params.golesEnContra && params.golesDePenal && params.penalesErrados) {
							def datosDeLasFechas = unJugador.getDatosDelasFechas();
							Iterator<DatosDeUnaFecha> it = datosDeLasFechas.iterator();
							Boolean repetido = false;

							while (it.hasNext() && !repetido) {
								if (it.next().getFecha() == Integer.parseInt(params.fecha)) repetido = true;
							}
			
							if (repetido) {
								flash.errorMessage = "Ya existen puntajes de este jugador para esa fecha!";
								return render(view: "create", model: [jugador: unJugador]);
							}
							else {
								DatosDeUnaFecha datos = new DatosDeUnaFecha(fecha: params.fecha, puntajeDelDiario: params.puntajeDelDiario,
																	golesAfavor: params.golesAfavor, golesEnContra: params.golesEnContra,
																	golesDePenal: params.golesDePenal, penalesErrados: params.penalesErrados,
																	penalesAtajados: 0, golesRecibidos: 0,
																	amonestado: false, expulsado: false, chenemigo: false,
																	figura: false, jugo: false);

								if (params.penalesAtajados) datos.setPenalesAtajados(Integer.parseInt(params.penalesAtajados));
								if (params.golesRecibidos) datos.setGolesRecibidos(Integer.parseInt(params.golesRecibidos));
								if (params.amonestado) datos.setAmonestado(true);
								if (params.expulsado) datos.setExpulsado(true);
								if (params.chenemigo) datos.setChenemigo(true);
								if (params.figura) datos.setFigura(true);
								if (params.jugo) datos.setJugo(true);

								if (datos.save()) {
									unJugador.addToDatosDelasFechas(datos);
									if (unJugador.save()) {
										flash.message = "Puntajes creados correctamente";
										return redirect(controller: "jugador", action: "showStatistics", id: unJugador.getId());
									}
									else {
										flash.errorMessage = "Error al agregar los puntajes al jugador";
										return redirect(controller: "jugador", action: "showStatistics", id: unJugador.getId());
									}
								}
								else {
									flash.errorMessage = "Error al crear los puntajes";
									return redirect(controller: "jugador", action: "showStatistics", id: unJugador.getId());
								}
							}
					}
					else return render(view: "create", model: [jugador: unJugador]);
				}
				else {
					flash.errorMessage = "No exite jugador con ese identificador";
					return render(view: "index");
				}
			}
			else {
				flash.errorMessage = "No hay jugador";
				return render(view: "index");
			}
		}
		else {
			flash.errorMessage = "No tenes permisos para editar los puntajes de un jugador!";
			return render(view: "index");
		}
	}

}
