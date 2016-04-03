package workspacegrails

class Liga {

    static constraints = {
		participantes(nullable: true);
		puntajesGenerales(nullable: true);
		puntajesPorFecha(nullable: true);
		nombre(nullable: true, unique: true);
		torneoPrincipal(nullable: true);
		fechasJugadas(nullable: true);
		fechaActual(nullable: true);
		fechaDeInicio(nullable: true, min: 1, max: Integer.MAX_VALUE);
		fechaDeFin(nullable: true, min: 1, max: Integer.MAX_VALUE);
		premioPorFechaGanada(nullable: true);
		premioPorFechaPerdida(nullable: true);
		premioPorTorneoGanado(nullable: true);
		premioPorTorneoPerdido(nullable: true);
    }

	static hasMany = [participantes: Usuario];

	SortedMap<Usuario, Float> puntajesGenerales;
	SortedMap<Usuario, SortedMap<Integer, Float>> puntajesPorFecha;
	String nombre;
	Boolean torneoPrincipal;
	Map<Integer, Usuario> fechasJugadas;
	FechaActual fechaActual;
	Integer fechaDeInicio;
	Integer fechaDeFin;
	Premio premioPorFechaGanada;
	Premio premioPorFechaPerdida;
	Premio premioPorTorneoGanado;
	Premio premioPorTorneoPerdido;

	public void addToFechasJugadas(Integer unaFecha) {
		if (!this.fechasJugadas) this.fechasJugadas = new HashMap<Integer, Usuario>();
		this.fechasJugadas[unaFecha] = null;
	}

	public void removeFromFechasJugadas(Integer unaFecha) {
		this.remove(unaFecha);
	}

	public void finalizarFecha() {
		this.addToFechasJugadas(this.fechaActual.getNumeroDeFecha());
		this.fechaActual.finalizarFecha();
	}

	public void finalizarTorneo() {
		this.fechasJugadas.clear();
	}

	public Boolean comenzoElTorneo() {
		if (!this.fechasJugadas && !this.fechaActual.comenzoLaFecha()) return false;
		if (this.fechasJugadas && this.fechasJugadas.isEmpty() && !this.fechaActual.comenzoLaFecha()) return false;
		return true;
	}

	public void actualizarTorneo() {
		if (!this.puntajesGenerales) this.puntajesGenerales = new TreeMap<Usuario, Float>();
		if (!this.puntajesPorFecha) this.puntajesPorFecha = new TreeMap<Usuario, SortedMap<Integer, Float>>();

		Iterator<Usuario> it = this.participantes.iterator();
		while (it.hasNext()) {
			Usuario unUsuario = it.next();
			this.puntajesGenerales.put(unUsuario, this.getPuntajeDelParticipanteEnElTorneo(unUsuario));

			for (Integer unaFecha : this.fechasJugadas.keySet()) {
				if (!this.puntajesPorFecha[unUsuario]) this.puntajesPorFecha.put(unUsuario, new TreeMap<Integer, Float>());
				this.puntajesPorFecha[unUsuario].put(unaFecha, this.getPuntajeDelParticipanteParaLaFecha(unUsuario, unaFecha));
			}

		}
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	private Float getPuntajeDelParticipanteParaLaFecha(Usuario unUsuario, Integer unaFecha) {
		if (this.fechaActual.getNumeroDeFecha() != unaFecha) return unUsuario.getPuntajeDeLaFecha(unaFecha);
		return 0.0f;
	}

	private Float getPuntajeDelParticipanteEnElTorneo(Usuario unUsuario) {
		Float puntajeTotal = 0.0f;

		for (Integer unaFecha : this.fechasJugadas.keySet()) {
			puntajeTotal += this.getPuntajeDelParticipanteParaLaFecha(unUsuario, unaFecha);
		}

		return puntajeTotal;
	}


}
