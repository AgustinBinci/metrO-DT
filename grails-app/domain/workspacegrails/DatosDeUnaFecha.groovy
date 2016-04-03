package workspacegrails

class DatosDeUnaFecha implements Comparable{

    static constraints = {
		fecha(nullable: true, min: 1, max: Integer.MAX_VALUE);
		puntajeDelDiario(nullable: true, min: 0, max: Float.MAX_VALUE);
		golesAfavor(nullable: true, min: 0, max: Integer.MAX_VALUE);
		golesEnContra(nullable: true, min: 0, max: Integer.MAX_VALUE);
		golesDePenal(nullable: true, min: 0, max: Integer.MAX_VALUE);
		penalesErrados(nullable: true, min: 0, max: Integer.MAX_VALUE);
		penalesAtajados(nullable: true, min: 0, max: Integer.MAX_VALUE);
		golesRecibidos(nullable: true, min: 0, max: Integer.MAX_VALUE);
		amonestado(nullable: true);
		expulsado(nullable: true);
		chenemigo(nullable: true);
		figura(nullable: true);
		jugo(nullable: true);
    }

	Integer fecha;
	Float puntajeDelDiario;
	Integer golesAfavor;
	Integer golesEnContra;
	Integer golesDePenal;
	Integer penalesErrados;
	Integer penalesAtajados;
	Integer golesRecibidos;
	Boolean amonestado;
	Boolean expulsado;
	Boolean chenemigo;
	Boolean figura;
	Boolean jugo;

	
	public DatosDeUnaFecha() {
		this.fecha = 0;
		this.puntajeDelDiario = 0.0;
		this.golesAfavor = 0;
		this.golesEnContra = 0;
		this.golesDePenal = 0;
		this.penalesErrados = 0;
		this.penalesAtajados = 0;
		this.golesRecibidos = 0;
		this.amonestado = false;
		this.expulsado = false;
		this.chenemigo = false;
		this.figura = false;
		this.jugo = false;
	}

	public int compareTo(Object objeto) {
		return (this.fecha - ((DatosDeUnaFecha)objeto).getFecha());
	}	
	
}
