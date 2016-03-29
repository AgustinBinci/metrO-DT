package workspacegrails

class JugadorSeleccionado {

    static constraints = {
		titular(nullable: true);
		jugador(nullable: true);
    }

	Boolean titular;
	Jugador jugador;

	public Float getPuntajeTotalDeLaFecha(Integer unaFecha) {
		return (this.jugador.getPuntajeTotalDeLaFecha(unaFecha));
	}

	public Integer getPrecio() {
		return (this.jugador.getPrecio());
	}

	public Posicion getPosicion() {
		this.jugador.getPosicion();
	}

	public Boolean jugoLaFecha(Integer unaFecha) {
		return (this.jugador.jugoLaFecha(unaFecha));
	}

	public Float getPuntajeTotal() {
		return (this.jugador.getPuntajeTotal());
	}

	public def getIdJugador() {
		return this.jugador.getId();
	}

	public String getNombre() {
		return this.jugador.getNombre();
	}

	public Club getClub() {
		return this.jugador.getClub();
	}

	@Override
	public String toString() {
		return this.jugador.getNombre();
	}
	
}
