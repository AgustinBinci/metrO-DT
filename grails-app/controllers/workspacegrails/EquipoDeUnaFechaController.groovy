package workspacegrails

import grails.converters.*

class EquipoDeUnaFechaController {

	def coleccionesService;
	def equipoDeUnaFechaService;

	def index() {
		return redirect (uri: "/");
	}

	def edit() {
		if (session.user) {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
			if (unEquipo) {
				if (params.nombre) {
					unEquipo.setNombre(params.nombre);
					if (unEquipo.save()) {
						Comparator<EquipoDeUnaFecha> unComparador = new Comparator<EquipoDeUnaFecha>() {
							public int compare(EquipoDeUnaFecha equipo, EquipoDeUnaFecha otroEquipo) {
								return (equipo.getFecha() - otroEquipo.getFecha());
							}
						}
						if (coleccionesService.sePuedeAgregarObjetoAlaColeccion(unEquipo, unUsuario.getEquipos(), unComparador)) {
							unUsuario.addToEquipos(unEquipo);
							if (unUsuario.save()) {
								flash.message = "Equipo creado correctamente";
								return redirect (action: "show");
							}
							else {
								flash.errorMessage = "No se pudo agregar el equipo al usuario";
								return redirect (action: "show");
							}
						}
						else {
							flash.errorMessage = "Ya hay un equipo para esa fecha!";
							return render (view: "create", model: [idUsuario: params.idUsuario, equipo: unEquipo]);
						}
					}
					else {
						flash.errorMessage = "No se pudo guardar el equipo";
						return render (view: "index");
					}
				}
				else return render (view: "create", model: [idUsuario: params.idUsuario, equipo: unEquipo]);
			}
			else {
				flash.errorMessage = "No hay equipo";
				return render (view: "index");
			}
		}
		else return render (view: "index");
	}

	def show() {
		if (session.user) {
			if (params.idUsuario) {
				Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);
				Usuario unUsuario = Usuario.findById(params.idUsuario);
				EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
				if (unEquipo) return render(view: "show", model: [usuario: unUsuario, equipo: unEquipo, torneo: torneoPrincipal]);
				else {
					flash.errorMessage = "No hay equipo";
					return redirect (action: "index");
				}
			}
			else return redirect (action: "index");
		}
		else return render (view: "index");
	}

	def actualizarJugadores() {
		if (session.user) {
			//Parametros de busqueda
			def parametros = [:];
			def jugadores = null;
			if (params.idPosicion && params.idPosicion != "null") parametros['posicion'] = Posicion.findById(params.idPosicion);
			if (params.idClub && params.idClub != "null") {
				parametros['club'] = Club.findById(params.idClub);
				jugadores = Jugador.findAllWhere(parametros);
			}
			if (jugadores) parametros['jugadores'] = jugadores;
			return render(template: "jugadores", model: parametros);
		}
	}

	def eliminarJugador() {
		if (session.user) {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (!unUsuario.isAttached()) unUsuario.attach();
			EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
			if (unEquipo) {
				if (!unEquipo.isAttached()) unEquipo.attach();
				Integer jugadoresDeUnMismoClubPermitidos = OpcionesDelJuego.first().getJugadoresDeUnMismoClubPermitidos();
				Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);

				if (torneoPrincipal && !torneoPrincipal.comenzoElTorneo()) {
					if (params.idJugadorSale) {
						JugadorSeleccionado unJugadorSeleccionado = JugadorSeleccionado.findById(params.idJugadorSale);
						if (unJugadorSeleccionado) {
							precioDelJugador = unJugadorSeleccionado.getPrecio();
							if (equipoDeUnaFechaService.eliminarJugadorDelEquipo(unJugadorSeleccionado, unEquipo)) 
								unUsuario.setPresupuesto(unUsuario.getPresupuesto() + precioDelJugador);
						}
					}
				}
				else flash.errorMessage = "Ya inicio el torneo!";
			
				//Render
				return render(template: "jugadoresDelEquipo", model: [usuario: unUsuario, equipo: unEquipo]);
			}
		}
	}

	def incorporarJugador() {
		if (session.user) {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (!unUsuario.isAttached()) unUsuario.attach();
			EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
			if (unEquipo) {
				if (!unEquipo.isAttached()) unEquipo.attach();
				Integer jugadoresDeUnMismoClubPermitidos = OpcionesDelJuego.first().getJugadoresDeUnMismoClubPermitidos();
				Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);

				if (torneoPrincipal && !torneoPrincipal.comenzoElTorneo()) {
					//Parametros del cambio
					if (params.idJugadorIngresa && params.esTitular) {
						boolean esTitular = false;
						if (params.esTitular == "true") esTitular = true;
						Jugador unJugador = Jugador.findById(params.idJugadorIngresa);
						if (unJugador) {
							try {
								equipoDeUnaFechaService.usuarioIncorporaElJugadorDeTitularidadAlEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(unUsuario, unJugador, 									esTitular, unEquipo, jugadoresDeUnMismoClubPermitidos);
								flash.message = unJugador.getNombre() + " incorporado";
							}
							catch (Exception unaExcepcion) {
								flash.errorMessage = unaExcepcion.getMessage();
							}
						}
					}
				}
				else flash.errorMessage = "Ya inicio el torneo!";
			
				//Render
				return render(template: "jugadoresDelEquipo", model: [usuario: unUsuario, equipo: unEquipo]);
			}
		}
	}

	def modify() {
		if (session.user) {
			Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (!unUsuario.isAttached()) unUsuario.attach();
			EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
			if (unEquipo) {
				if (!unEquipo.isAttached()) unEquipo.attach();

				//Parametros de busqueda
				def parametros = [:];
				parametros['clubes'] = Club.list();
				parametros['posiciones'] = Posicion.list();

				//Cambios
				boolean cambioGranDt = false;
				boolean cambioInterno = false;
				boolean cambioDeSuplente = false;
				Integer precioDelJugador;
				Integer jugadoresDeUnMismoClubPermitidos = OpcionesDelJuego.first().getJugadoresDeUnMismoClubPermitidos();

				//Cambio gran dt
				if (params.cambioGranDt && params.cambioGranDt == "true") {
					if (torneoPrincipal && torneoPrincipal.comenzoElTorneo() && !torneoPrincipal.getFechaActual().comenzoLaFecha()) {
						if (params.idJugadorSale && params.idJugadorIngresa) {
							JugadorSeleccionado unJugadorSeleccionado = JugadorSeleccionado.findById(params.idJugadorSale);
							Jugador unJugador = Jugador.findById(params.idJugadorIngresa);
							if (unJugadorSeleccionado && unJugador) {
								try {
									usuarioCambiaElJugadorPorElJugadorDelEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(unUsuario, unJugador, 
																						unJugadorSeleccionado, unEquipo, jugadoresDeUnMismoClubPermitidos);
									flash.message = "Cambio realizado correctamente";
								}
								catch(Exception unaExcepcion) {
									flash.errorMessage = unaExcepcion.getMessage();
								}
								parametros['cambioGranDt'] = true;
							}
						}
					}
					else {
						//Cambio interno
						if (params.cambioInterno && params.cambioInterno == "true") {
							if (torneoPrincipal && torneoPrincipal.comenzoElTorneo() && !torneoPrincipal.getFechaActual().comenzoLaFecha()) {
								if (params.idJugadorSale) {
									JugadorSeleccionado jugadorQueSale = JugadorSeleccionado.findById(params.idJugadorSale);
									if (jugadorQueSale) {
										try {
											usuarioCambiaInternamenteElJugadorDelEquipo(unUsuario, jugadorQueSale, unEquipo);
											flash.message = "Cambio realizado correctamente";
										}
										catch(Exception unaExcepcion) {
											flash.errorMessage = unaExcepcion.getMessage();
										}
										parametros['cambioInterno'] = true;
									}
								}
							}
							else return redirect(action: "show", params: [idUsuario: unUsuario.getId()]);
						}
						else {
							//Modificaciones iniciales
							if (torneoPrincipal && !torneoPrincipal.comenzoElTorneo()) {
								//Parametros del cambio
								if (params.idJugadorSale) {
									JugadorSeleccionado unJugadorSeleccionado = JugadorSeleccionado.findById(params.idJugadorSale);
									if (unJugadorSeleccionado) {
										precioDelJugador = unJugadorSeleccionado.getPrecio();
										if (equipoDeUnaFechaService.eliminarJugadorDelEquipo(unJugadorSeleccionado, unEquipo)) 
											unUsuario.setPresupuesto(unUsuario.getPresupuesto() + precioDelJugador);
									}
								}
								if (params.idJugadorIngresa && params.esTitular) {
									boolean esTitular = false;
									if (params.esTitular == "true") esTitular = true;
									Jugador unJugador = Jugador.findById(params.idJugadorIngresa);
									if (unJugador) {
										try {
											equipoDeUnaFechaService.usuarioIncorporaElJugadorDeTitularidadAlEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(unUsuario, unJugador, 									esTitular, unEquipo, jugadoresDeUnMismoClubPermitidos);
											flash.message = unJugador.getNombre() + " incorporado";
										}
										catch (Exception unaExcepcion) {
											flash.errorMessage = unaExcepcion.getMessage();
										}
									}
								}

								//render
								//if (!unEquipo.isAttached()) unEquipo.attach();
								//if (!unUsuario.isAttached()) unUsuario.attach();
							}
							else {
								flash.errorMessage = "Ya inicio el torneo!";
								return redirect (show: "show", params: [idUsuario: unUsuario.getId()]);
							}

						}
					}
				}
				
				//Renderizo		
				parametros['equipo'] = unEquipo;
				parametros['usuario'] = unUsuario;
				return render (view: "modify", model: parametros);
			}
			else {
				flash.errorMessage = "No hay equipo";
				return redirect (action: "index");
			}
		}
		else return render (view: "index");
	}

	def cambioGranDt() {
		if (session.user) {
			Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (!unUsuario.isAttached()) unUsuario.attach();
			EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
			if (unEquipo) {
				if (!unEquipo.isAttached()) unEquipo.attach();
				if (unUsuario.getCambiosGranDt() > 0 && torneoPrincipal && torneoPrincipal.comenzoElTorneo() && !torneoPrincipal.getFechaActual().comenzoLaFecha()) {

					//Parametros de busqueda
					def parametros = [:];
					def jugadores = null;
					if (params.idPosicion && params.idPosicion != "null") parametros['posicion'] = Posicion.findById(params.idPosicion);
					if (params.idClub && params.idClub != "null") {
						parametros['club'] = Club.findById(params.idClub);
						jugadores = Jugador.findAllWhere(parametros);
					}
					if (jugadores) parametros['jugadores'] = jugadores;
					parametros['clubes'] = Club.list();
					parametros['posiciones'] = Posicion.list();

					//Parametros del cambio
					Integer precioDelJugador;

					if (params.idJugadorSale) {
						JugadorSeleccionado unJugadorSeleccionado = JugadorSeleccionado.findById(params.idJugadorSale);
						if (unJugadorSeleccionado) {
							precioDelJugador = unJugadorSeleccionado.getPrecio();
							if (equipoDeUnaFechaService.eliminarJugadorDelEquipo(unJugadorSeleccionado, unEquipo)) 
								unUsuario.setPresupuesto(unUsuario.getPresupuesto() + precioDelJugador);
						}
					}
					if (params.idJugadorIngresa && params.esTitular) {
						boolean esTitular = false;
						if (params.esTitular == "true") esTitular = true;
						Jugador unJugador = Jugador.findById(params.idJugadorIngresa);
						if (unJugador) {
							try {
								equipoDeUnaFechaService.usuarioIncorporaElJugadorDeTitularidadAlEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(unUsuario, unJugador, 									esTitular, unEquipo, OpcionesDelJuego.first().getJugadoresDeUnMismoClubPermitidos());
								flash.message = unJugador.getNombre() + " incorporado";
							}
							catch (Exception unaExcepcion) {
								flash.errorMessage = unaExcepcion.getMessage();
							}
						}
					}

					//render
					//if (!unEquipo.isAttached()) unEquipo.attach();
					//if (!unUsuario.isAttached()) unUsuario.attach();
					parametros['equipo'] = unEquipo;
					parametros['usuario'] = unUsuario;
					return render (view: "modify", model: parametros);
				}
				else {
					flash.errorMessage = "Ya inicio el torneo!";
					return redirect (show: "show", params: [idUsuario: unUsuario.getId()]);
				}
			
			}
			else {
				flash.errorMessage = "No hay equipo";
				return redirect (action: "index");
			}
		}
		else return render (view: "index");

	}

}
