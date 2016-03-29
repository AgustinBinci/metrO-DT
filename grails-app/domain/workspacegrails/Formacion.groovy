package workspacegrails

class Formacion {

    static constraints = {
		cantidadDeDelanteros(nullable: true, min: 0, max: 4);
		cantidadDeVolantes(nullable: true, min: 0, max: 7);
		cantidadDeDefensores(nullable: true, min: 0, max: 7);
		cantidadDeArqueros(nullable: true, min: 1, max: 1);
    }

	Integer cantidadDeDelanteros;
	Integer cantidadDeVolantes;
	Integer cantidadDeDefensores;
	static final Integer cantidadDeArqueros = 1;


	@Override
	public String toString() {
		return (this.cantidadDeDefensores.toString() + "-" + this.cantidadDeVolantes.toString() + "-" + this.cantidadDeDelanteros.toString());
	}

}
