package workspacegrails

abstract class Posicion {

    static constraints = {
		valorPorGolAfavor(nullable: true);
		valorPorGolEnContra(nullable: true);
		valorPorGolDePenal(nullable: true);
		valorPorPenalErrado(nullable: true);
		valorPorPenalAtajado(nullable: true);
		valorPorGolRecibido(nullable: true);
		valorPorVallaInvicta(nullable: true);
		valorPorAmonestacion(nullable: true);
		valorPorExpulsion(nullable: true);
		valorPorChenemigo(nullable: true);
		valorPorFigura(nullable: true);
    }

	Integer valorPorGolAfavor;
	Integer valorPorGolEnContra;
	Integer valorPorGolDePenal;
	Integer valorPorPenalErrado;
	Integer valorPorPenalAtajado;
	Integer valorPorGolRecibido;
	Integer valorPorVallaInvicta;
	Integer valorPorAmonestacion;
	Integer valorPorExpulsion;
	Integer valorPorChenemigo;
	Integer valorPorFigura;

	public Posicion() {
		this.valorPorGolEnContra = -3;
		this.valorPorGolDePenal = 2;
		this.valorPorPenalErrado = -2;
		this.valorPorPenalAtajado = 0;
		this.valorPorGolRecibido = 0;
		this.valorPorVallaInvicta = 0;
		this.valorPorAmonestacion = -1;
		this.valorPorExpulsion = -3;
		this.valorPorChenemigo = -2;
		this.valorPorFigura = 2;
	}

	public abstract Integer getCantidadDeJugadores(Formacion unaFormacion);

	@Override
	public String toString() {
		return (this.getClass().getSimpleName());
	}

}
