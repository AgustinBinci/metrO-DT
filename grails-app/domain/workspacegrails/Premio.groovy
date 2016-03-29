package workspacegrails

class Premio {

    static constraints = {
    }

	static hasMany = [cambiosAeleccion: CambioAeleccion];

	String descripcion;
	Integer presupuestoAdicional;

	@Override
	String toString() {
		return this.descripcion;
	}
	
}
