package workspacegrails

class CambioAeleccion {

    static constraints = {
		cambioInterno(nullable: true);
		cambioGranDt(nullable: true);
		cambioDeSuplentes(nullable: true);
    }

	Boolean cambioInterno;
	Boolean cambioGranDt;
	Boolean cambioDeSuplentes;

}
