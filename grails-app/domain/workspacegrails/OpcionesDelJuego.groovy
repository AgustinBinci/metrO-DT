package workspacegrails

class OpcionesDelJuego {

    static constraints = {
		presupuestoInicial(nullable: true, max: 9999999999999);
		cambiosInternosIniciales(nullable: true, min: 0, max: 9999999999999);
		cambiosGranDtIniciales(nullable: true, min: 0, max: 9999999999999);
		cambiosDeSuplenteIniciales(nullable: true, min: 0, max: 9999999999999);
		cambiosAeleccionIniciales(nullable: true);
		cambiosDeSuplenteIniciales(nullable: true, min: 1, max: 9999999999999);
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
