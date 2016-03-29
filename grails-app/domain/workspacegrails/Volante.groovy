package workspacegrails

class Volante extends Posicion {

    static constraints = {
    }

	public Volante() {
		super();	
		this.valorPorGolAfavor = 4;
	}

	public Integer getCantidadDeJugadores(Formacion unaFormacion) {
		return unaFormacion.getCantidadDeVolantes();
	}

}
