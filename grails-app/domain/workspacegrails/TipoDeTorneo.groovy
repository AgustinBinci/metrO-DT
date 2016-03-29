package workspacegrails

abstract class TipoDeTorneo {

    static constraints = {
    }

	CriterioDeSeleccion criterioDeSeleccion;
	Integer fechaDeInicio;
	Integer fechaDeFin;
	Integer porcentajeDeParticipantes;

	public abstract def seleccionarParticipantes(def participantes);

	@Override
	public String toString() {
		return (this.getClass().getSimpleName());
	}

}
