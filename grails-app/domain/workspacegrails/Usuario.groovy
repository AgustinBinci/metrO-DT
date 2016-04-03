package workspacegrails

class Usuario implements Comparable<Usuario> {

    static constraints = {
		nombre(nullable: true, blank: false, size: 1..50)
		contrasenia(nullable: true, blank: false, size: 1..255, password: true);
		email(nullable: true, blank: false, size: 1..50, email:true, unique: true);
		admin(nullable: true);
		presupuesto(nullable: true, max: Integer.MAX_VALUE);
		cambiosGranDt(nullable: true, min: 0, max: Integer.MAX_VALUE);
		cambiosInternos(nullable: true, min: 0, max: Integer.MAX_VALUE);
		cambiosDeSuplente(nullable: true, min: 0, max: Integer.MAX_VALUE);
		equipos(nullable: true);
		cambiosAeleccion(nullable: true);
    }

	String nombre;
	String nombreDelEquipo;
	String apellido;
	String contrasenia;
	String email;
	Boolean admin;
	Integer presupuesto;
	Integer cambiosGranDt;
	Integer cambiosInternos;
	Integer cambiosDeSuplente;
	List<EquipoDeUnaFecha> equipos;
	List<CambioAeleccion> cambiosAeleccion;

	public void addToEquipos(EquipoDeUnaFecha unEquipo) {
		if (!this.equipos) this.equipos = new ArrayList<EquipoDeUnaFecha>();
		this.equipos.add(unEquipo);
	}

	public EquipoDeUnaFecha getEquipoDeLaFecha(Integer unaFecha) {
		Iterator<EquipoDeUnaFecha> it = this.equipos.iterator();
		while (it.hasNext()) {
			EquipoDeUnaFecha unEquipo = it.next();
			if (unEquipo.getFecha() == unaFecha) return unEquipo;
		}
		return null;
	}

	public Float getPuntajeDeLaFecha(Integer unaFecha) {
		EquipoDeUnaFecha unEquipo = this.getEquipoDeLaFecha(unaFecha);
		if (unEquipo) return unEquipo.getPuntaje();
		else return 0.0f;
	}

	@Override
	public int compareTo(Usuario otroUsuario) {
		if (this.id == otroUsuario.getId() && this.email == otroUsuario.getEmail()) return 0;
		else return -1;
	}

}
