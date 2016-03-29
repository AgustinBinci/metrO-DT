package workspacegrails

class Arquero extends Posicion {

    static constraints = {
    }

	public Arquero() {
		super();
		this.valorPorGolAfavor = 6;
		this.valorPorPenalAtajado = 3;
		this.valorPorGolRecibido = -1;
		this.valorPorVallaInvicta = 2;
	}

	public Integer getCantidadDeJugadores(Formacion unaFormacion) {
		return unaFormacion.getCantidadDeArqueros();
	}

}
