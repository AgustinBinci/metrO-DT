package workspacegrails

class ParticipanteDeUnTorneo {

    static constraints = {
    }
	
	Usuario usuario;
	Float puntajeTotal;
	Map<Integer, Float> puntajesPorFecha;

}
