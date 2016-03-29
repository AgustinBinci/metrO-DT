package workspacegrails

import grails.transaction.Transactional

class EquipoDeUnaFechaService {

	@Transactional
    boolean eliminarJugadorDelEquipo(JugadorSeleccionado unJugador, EquipoDeUnaFecha unEquipo) {
		Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
			while (it.hasNext()) {
				if (it.next().getId() == unJugador.getId()) {
				unEquipo.removeFromJugadoresSeleccionados(unJugador);
				unEquipo.save();
				unJugador.delete();
				return true;
			}
		}
		return false;
	}

	@Transactional
	def usuarioCambiaElJugadorPorElJugadorDelEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(Usuario unUsuario, def jugadorQueIngresa, def jugadorQueSale, 
																				EquipoDeUnaFecha unEquipo, Integer cantidadMaximaDeJugadoresPermitidos) {
		if (unUsuario.getCambiosGranDt() > 0) {
			Integer precioDelJugadorQueSale = jugadorQueSale.getPrecio();
			Integer presupuestoAnterior = unUsuario.getPresupuesto();
			Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
			while (it.hasNext()) {
				if (it.next().getId() == jugadorQueSale.getId()) {
					unEquipo.removeFromJugadoresSeleccionados(jugadorQueSale);
					unUsuario.setPresupuesto(unUsuario.getPresupuesto() + precioDelJugadorQueSale);
				}
			}

			try {
				this.usuarioIncorporaElJugadorDeTitularidadAlEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(unUsuario, jugadorQueIngresa, jugadorQueSale.getTitular(), 
																								unEquipo, cantidadMaximaDeJugadoresPermitidos);
				jugadorQueSale.delete();
				unUsuario.setCambiosGranDt(unUsuario.getCambiosGranDt() - 1);
				unUsuario.save();
			}
			catch (Exception unaExcepcion) {
				unEquipo.addToJugadoresSeleccionados(jugadorQueSale);
				unUsuario.setPresupuesto(presupuestoAnterior);
				throw unaExcepcion;
			}
		}
		else throw new Exception("No tenes cambios Gran DT!");
	}

	@Transactional
	def usuarioCambiaInternamenteElJugadorDelEquipo(Usuario unUsuario, JugadorSeleccionado jugadorQueSale, EquipoDeUnaFecha unEquipo) {
		if (unUsuario.getCambiosInternos() > 0) {
			if (jugadorQueSale.getTitular()) {
				Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
				JugadorSeleccionado unJugador;
				while (it.hasNext()) {
					unJugador = it.next();
					if (!unJugador.getTitular() && unJugador.getPosicion().getId() == jugadorQueSale.getPosicion().getId()) {
						unJugador.setTitular(true);
						jugadorQueSale.setTitular(false);
						unJugador.save();
						jugadorQueSale.save();
						unUsuario.setCambiosInternos(unUsuario.getCambiosInternos() - 1);
					}
				}
			}
			else throw new Exception("El jugador tiene que ser titular para realizar este cambio!");			
		}
		else throw new Exception("No tenes cambios internos!");
	}

	@Transactional
	def usuarioIncorporaElJugadorDeTitularidadAlEquipoConMaximoDeJugadoresDeUnMismoClubPermitidos(Usuario unUsuario, def unJugador, boolean esTitular, 
																				EquipoDeUnaFecha unEquipo, Integer cantidadMaximaDeJugadoresPermitidos) {
		if (this.existeJugadorEnElEquipo(unJugador, unEquipo)) throw new Exception("Ya tenes a este jugador!");
		if (!this.tienePresupuestoElUsuarioParaIncorporarAlJugador(unUsuario, unJugador)) throw new Exception("No te da el presupuesto para esta incorporacion!");
		if (!this.tieneElEquipoCupoDeJugadoresDeUnMismoClubParaElJugador(cantidadMaximaDeJugadoresPermitidos, unEquipo, unJugador)) 
			throw new Exception("Ya tenes lleno el cupo maximo de jugadores permitidos de ese club");
		if (esTitular && !this.tieneElEquipoCupoParaLaPosicionEnLosTitulares(unEquipo, unJugador.getPosicion()))
			throw new Exception("No podes poner mas " + unJugador.getPosicion().toString() + "s titulares!");
		else if (!this.tieneElEquipoCupoParaLaPosicionEnLosSuplentes(unEquipo, unJugador.getPosicion()))
			throw new Exception("No podes poner mas " + unJugador.getPosicion().toString() + "s suplentes!");
		this.agregarJugadorDeCondicionAlEquipo(unJugador, esTitular, unEquipo);
		if (unUsuario.save()) unUsuario.setPresupuesto(unUsuario.getPresupuesto() - unJugador.getPrecio());
		else throw new Exception("No se pudo incorporar a " + unJugador.getNombre());
	}

	//********************************************************************************Privados**********************************************************************************

	private boolean existeJugadorEnElEquipo(def unJugador, EquipoDeUnaFecha unEquipo) {
		Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
		while (it.hasNext()) {
			if (it.next().getIdJugador() == unJugador.getId()) return true;
		}
		return false;
	}

	private boolean tienePresupuestoElUsuarioParaIncorporarAlJugador(Usuario unUsuario, def unJugador) {
		return (unUsuario.getPresupuesto() >= unJugador.getPrecio());
	}

	private boolean tieneElEquipoCupoDeJugadoresDeUnMismoClubParaElJugador(Integer cantidadMaximaDeJugadoresPermitidos, EquipoDeUnaFecha unEquipo, def unJugador) {
		Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
		Integer cantidad = 0;

		while (it.hasNext()) {
			if (it.next().getClub().getId() == unJugador.getClub().getId()) cantidad ++;
		}

		return (cantidad < cantidadMaximaDeJugadoresPermitidos);
	}

	private boolean tieneElEquipoCupoParaLaPosicionEnLosTitulares(EquipoDeUnaFecha unEquipo, Posicion unaPosicion) {
		def formaciones = Formacion.list();
		if (formaciones) {
			Integer cantidadMaximaDeJugadoresEnEsaPosicion = 0;
			Integer cantidadDeJugadoresEnEsaPosicion = 0;
			Integer cantidadDeJugadoresTotal = 0;
			Integer cantidadDeJugadores = 0;
			Iterator<Formacion> it = formaciones.iterator();
			Formacion unaFormacion;
			Iterator<JugadorSeleccionado> unIt = unEquipo.getJugadoresSeleccionados().iterator();
			JugadorSeleccionado unJugador;

			//Averiguo cantidad maxima y minima posible para esa posicion
			while (it.hasNext()) {
				unaFormacion = it.next();
				cantidadDeJugadores = unaPosicion.getCantidadDeJugadores(unaFormacion);
				if (cantidadDeJugadores > cantidadMaximaDeJugadoresEnEsaPosicion) cantidadMaximaDeJugadoresEnEsaPosicion = cantidadDeJugadores;
			}

			//Averiguo la cnatidad de jugadores total y para esa posicion (titulares)
			while (unIt.hasNext()) {
				unJugador = unIt.next();
				if (unJugador.getTitular()) {
					if (unJugador.getPosicion().getId() == unaPosicion.getId()) cantidadDeJugadoresEnEsaPosicion ++;
					cantidadDeJugadoresTotal ++;
				}
			}

			//Logica principal
			return (cantidadDeJugadoresEnEsaPosicion < cantidadMaximaDeJugadoresEnEsaPosicion);
		}
		else return false;
	}

	private boolean tieneElEquipoCupoParaLaPosicionEnLosSuplentes(EquipoDeUnaFecha unEquipo, Posicion unaPosicion) {
		Iterator<JugadorSeleccionado> it = unEquipo.getJugadoresSeleccionados().iterator();
		JugadorSeleccionado unJugador;
		while (it.hasNext()) {
			unJugador = it.next();
			if (!unJugador.getTitular()) {
				if (unJugador.getPosicion().getId() == unaPosicion.getId()) return false;
			}
		}
		return true;
	}

	@Transactional
	private void agregarJugadorDeCondicionAlEquipo(Jugador unJugador, boolean esTitular, EquipoDeUnaFecha unEquipo) {
		JugadorSeleccionado unJugadorSeleccionado = new JugadorSeleccionado(jugador: unJugador, titular: esTitular);
		unJugadorSeleccionado.save();
		unEquipo.addToJugadoresSeleccionados(unJugadorSeleccionado);
		this.actualizarFormacionDelEquipo(unEquipo);
		unEquipo.save();
	}

	private void actualizarFormacionDelEquipo(EquipoDeUnaFecha unEquipo) {
		def formaciones = Formacion.list();
		Iterator<Formacion> itFormaciones = formaciones.iterator();
		Iterator<JugadorSeleccionado> itJugadores = unEquipo.getJugadoresSeleccionados().iterator();
		def cantidadDeJugadoresPorPosicion = ['Arquero': 0, 'Defensor': 0, 'Volante': 0, 'Delantero': 0];
		JugadorSeleccionado unJugador;
		Formacion unaFormacion;

		while (itJugadores.hasNext()) {
			unJugador = itJugadores.next();
			if (unJugador.getTitular()) cantidadDeJugadoresPorPosicion[unJugador.getPosicion().toString()] ++;
		}

		while (itFormaciones.hasNext()) {
			unaFormacion = itFormaciones.next();
			if (unaFormacion.getCantidadDeArqueros() >= cantidadDeJugadoresPorPosicion['Arquero']) {
				if (unaFormacion.getCantidadDeDefensores() >= cantidadDeJugadoresPorPosicion['Defensor']) {
					if (unaFormacion.getCantidadDeVolantes() >= cantidadDeJugadoresPorPosicion['Volante']) {
						if (unaFormacion.getCantidadDeDelanteros() >= cantidadDeJugadoresPorPosicion['Delantero']) {
							unEquipo.setFormacion(unaFormacion);
							break;
						}
					}
				}
			}
		}
	}

}
