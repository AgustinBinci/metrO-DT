package workspacegrails

abstract class Torneo {

    static constraints = {
		nombre(unique: true);
    }

	static hasMany = [participantes: Usuario];

	String nombre
	Boolean torneoPrincipal;
	List<Integer> fechas;
	FechaActual fechaActual;
	CriterioDeSeleccion criterioDeSeleccion;
	Integer fechaDeInicio;
	Integer fechaDeFin;
	Integer porcentajeDeParticipantes;

	public abstract void actualizarTorneo();

	public void addToFechas(Integer unaFecha) {
		if (!this.fechas) this.fechas = new ArrayList<Integer>();
		this.fechas.add(unaFecha);
	}

	public void removeFromFechas(Integer unaFecha) {
		this.remove(unaFecha);
	}

	public void seleccionarParticipantes(def participantes) {
		def participantesSeleccionados = this.criterioDeSeleccion.getParticipantesSeleccionados(participantes, this.porcentajeDeParticipantes);
		Iterator it = participantesSeleccionados.iterator();
		while (it.hasNext()) {
			this.addToParticipantes(it.next());
		}
	}

	public void finalizarFecha() {
		this.fechas.add(this.fechaActual.getNumeroDeFecha());
		this.fechaActual.finalizarFecha();
	}

	public void finalizarTorneo() {
		this.fechas.clear();
	}

	public Boolean comenzoElTorneo() {
		if (!this.fechas && !this.fechaActual.comenzoLaFecha()) return false;
		if (this.fechas && this.fechas.isEmpty() && !this.fechaActual.comenzoLaFecha()) return false;
		return true;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
