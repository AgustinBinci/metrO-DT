package workspacegrails

class FechaActual {

    static constraints = {
		fecha(nullable: true, min: 1, max: Integer.MAX_VALUE);
		fechaDeInicio(nullable: true);
		fechaIniciada(nullable: true);
		fechaFinalizada(nullable: true);
    }

	Integer numeroDeFecha;
	Calendar fechaDeInicio;
	Boolean fechaIniciada;
	Boolean fechaFinalizada;

	public FechaActual() {
		this.fechaFinalizada = false;
	}

	public void finalizarFecha() {
		this.numeroDeFecha ++;
		this.fechaIniciada = false;
		this.fechaDeInicio.add(Calendar.DATE, 7);
	}

	public Boolean comenzoLaFecha() {
		Calendar fechaActual = GregorianCalendar.getInstance();
		return fechaActual.after(this.fechaDeInicio);
	}

}
