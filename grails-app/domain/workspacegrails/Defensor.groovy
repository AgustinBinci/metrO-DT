package workspacegrails

class Defensor extends Posicion {

    static constraints = {
    }

	public Defensor() {
		super();
		this.valorPorGolAfavor = 5;
		this.valorPorVallaInvicta = 1;
	}

	public Integer getCantidadDeJugadores(Formacion unaFormacion) {
		return unaFormacion.getCantidadDeDefensores();
	}

}
