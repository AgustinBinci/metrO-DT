package workspacegrails

class Delantero extends Posicion {

    static constraints = {
    }

	public Delantero() {
		super();
		this.valorPorGolAfavor = 3;
	}

	public Integer getCantidadDeJugadores(Formacion unaFormacion) {
		return unaFormacion.getCantidadDeDelanteros();
	}

}
