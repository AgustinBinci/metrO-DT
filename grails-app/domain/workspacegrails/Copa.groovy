package workspacegrails

class Copa extends TipoDeTorneo {

    static constraints = {
    }

	Boolean faseDeGrupos;
	Boolean doblePartido;

	public def seleccionarParticipantes(def participantes) {
		Integer cantidadDeFechas = this.fechaDeFin - this.fechaDeInicio;
		
	}

}
