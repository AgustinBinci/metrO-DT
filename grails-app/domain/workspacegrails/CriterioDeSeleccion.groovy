package workspacegrails

abstract class CriterioDeSeleccion {

    static constraints = {
    }

	public abstract def getParticipantesSeleccionados(def participantes, Integer porcentajeDeParticipantes);

}
