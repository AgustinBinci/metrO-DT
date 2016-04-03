package workspacegrails

class EquipoDeUnaFecha {

    static constraints = {
		fecha(nullable: true, min: 1, max: Integer.MAX_VALUE);
		formacion(nullable: true);
    }

	static hasMany = [jugadoresSeleccionados: JugadorSeleccionado];

	Integer fecha;
	Formacion formacion;

	public Float getPuntaje() {
		Float unPuntaje = 0.0f;
		Iterator<JugadorSeleccionado> it = this.jugadoresSeleccionados.iterator();
		def usados = [Arquero: false, Defensor: false, Volante: false, Delantero: false];

		while (it.hasNext()) {
			JugadorSeleccionado unJugador = it.next();
			if (unJugador.getTitular()) {
				if (unJugador.jugoLaFecha(this.fecha)) unPuntaje += unJugador.getPuntajeTotalDeLaFecha(this.fecha);
				else {
					if (!usados.get(unJugador.getPosicion().toString())) {
						Iterator<JugadorSeleccionado> otroIt = this.jugadoresSeleccionados.iterator();
						while (otroIt.hasNext()) {
							JugadorSeleccionado jugadorSuplente = otroIt.next();
							if (!jugadorSuplente.getTitular() && jugadorSuplente.getPosicion() == unJugador.getPosicion()) {
								unPuntaje += jugadorSuplente.getPuntajeTotalDeLaFecha(this.fecha);
								usados[unJugador.getPosicion().toString()] = true;
							}
						}
					}
				}
			}			
		}

		return unPuntaje;
	}

}
