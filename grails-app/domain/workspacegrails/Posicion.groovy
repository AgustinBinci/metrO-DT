package workspacegrails

abstract class Posicion {

    static constraints = {
		valorPorGolAfavor(nullable: true, max: Integer.MAX_VALUE);
		valorPorGolEnContra(nullable: true, max: Integer.MAX_VALUE);
		valorPorGolDePenal(nullable: true, max: Integer.MAX_VALUE);
		valorPorPenalErrado(nullable: true, max: Integer.MAX_VALUE);
		valorPorPenalAtajado(nullable: true, max: Integer.MAX_VALUE);
		valorPorGolRecibido(nullable: true, max: Integer.MAX_VALUE);
		valorPorVallaInvicta(nullable: true, max: Integer.MAX_VALUE);
		valorPorAmonestacion(nullable: true, max: Integer.MAX_VALUE);
		valorPorExpulsion(nullable: true, max: Integer.MAX_VALUE);
		valorPorChenemigo(nullable: true, max: Integer.MAX_VALUE);
		valorPorFigura(nullable: true, max: Integer.MAX_VALUE);
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
