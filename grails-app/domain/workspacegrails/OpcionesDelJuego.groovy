package workspacegrails

class OpcionesDelJuego {

    static constraints = {
		presupuestoInicial(nullable: true, max: Integer.MAX_VALUE);
		cambiosInternosIniciales(nullable: true, min: 0, max: Integer.MAX_VALUE);
		cambiosGranDtIniciales(nullable: true, min: 0, max: Integer.MAX_VALUE);
		cambiosDeSuplenteIniciales(nullable: true, min: 0, max: Integer.MAX_VALUE);
		cambiosAeleccionIniciales(nullable: true);
		cambiosDeSuplenteIniciales(nullable: true, min: 1, max: Integer.MAX_VALUE);
    }

	static Integer presupuestoInicial = 10000000;
	static Integer cambiosInternosIniciales = 1;
	static Integer cambiosGranDtIniciales = 1;
	static Integer cambiosDeSuplenteIniciales = 0;
	List<CambioAeleccion> cambiosAeleccionIniciales;
	static Integer jugadoresDeUnMismoClubPermitidos = 3;


	public void setCambioAeleccion(CambioAeleccion unCambio) {
		if (!this.cambiosAeleccionIniciales) this.cambiosAeleccionIniciales = new ArrayList<CambioAeleccion>();
		this.cambiosAeleccionIniciales.add(unCambio);
	}

}
