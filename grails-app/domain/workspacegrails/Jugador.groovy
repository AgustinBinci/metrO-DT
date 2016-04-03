package workspacegrails

import java.util.List;

class Jugador {

    static constraints = {
		datosDelasFechas(nullable: true);
		nombre(nullable: true, blank: false, size: 1..50);
		precio(nullable: true, min: 0, max: Integer.MAX_VALUE);
		club(nullable: true);
		posicion(nullable: true);
    }

	static belongsTo = Club;
	static hasMany = [datosDelasFechas: DatosDeUnaFecha];
	String nombre;
	Integer precio;
	Posicion posicion;
	Club club;


	public Float getPuntajeTotalDeLaFecha(Integer unaFecha) {
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();
		Float puntajeTotal = 0.0f;
		Boolean encontrado = false;
		while (it.hasNext() && !encontrado){
			DatosDeUnaFecha datos = it.next();
			
			if (datos.getFecha() == unaFecha) {
				if (datos.getJugo()) {
					puntajeTotal += datos.getPuntajeDelDiario();
					puntajeTotal += (datos.getGolesAfavor() * this.posicion.getValorPorGolAfavor());
					puntajeTotal += (datos.getGolesEnContra() * this.posicion.getValorPorGolEnContra());
					puntajeTotal += (datos.getGolesDePenal() * this.posicion.getValorPorGolDePenal());
					puntajeTotal += (datos.getPenalesErrados() * this.posicion.getValorPorPenalErrado());
					puntajeTotal += (datos.getPenalesAtajados() * this.posicion.getValorPorPenalAtajado());

					if (datos.getGolesRecibidos() != 0) puntajeTotal += (datos.getGolesRecibidos() * this.posicion.getValorPorGolRecibido());
					else puntajeTotal += this.posicion.getValorPorVallaInvicta();

					if (datos.getExpulsado()) puntajeTotal += this.posicion.getValorPorExpulsion();
					else {
						if (datos.getAmonestado()) puntajeTotal += this.posicion.getValorPorAmonestacion();
					}

					if (datos.getChenemigo()) puntajeTotal += this.posicion.getValorPorChenemigo();
					if (datos.getFigura()) puntajeTotal += this.posicion.getValorPorFigura();
				}

				encontrado = true;
			}
		}

		return puntajeTotal;
	}

	public Boolean jugoLaFecha(Integer unaFecha) {
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();
		while (it.hasNext()){
			DatosDeUnaFecha datos = it.next();
			if (datos.getFecha() == unaFecha) {
				return datos.getJugo();
			}
		}
		return false;
	}

	public Float getPuntajeTotal() {
		Float puntajeTotal = 0.0f;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			puntajeTotal += this.getPuntajeTotalDeLaFecha(it.next().getFecha());
		}

		return puntajeTotal;
	}

	public Integer getCantidadDeGolesAfavor() {
		Integer cantidadDeGoles = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDeGoles += it.next().getGolesAfavor();
		}
	
		return cantidadDeGoles;
	}

	public Integer getCantidadDeGolesEnContra() {
		Integer cantidadDeGoles = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDeGoles += it.next().getGolesEnContra();
		}
	
		return cantidadDeGoles;
	}

	public Integer getCantidadDePenalesErrados() {
		Integer cantidadDePenales = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDePenales += it.next().getPenalesErrados();
		}
	
		return cantidadDePenales;
	}

	public Integer getCantidadDePenalesAtajados() {
		Integer cantidadDePenales = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDePenales += it.next().getPenalesAtajados();
		}
	
		return cantidadDePenales;
	}

	public Integer getCantidadDeGolesRecibidos() {
		Integer cantidadDeGoles = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDeGoles += it.next().getGolesRecibidos();
		}
	
		return cantidadDeGoles;
	}

	public Integer getCantidadDeGolesDePenales() {
		Integer cantidadDeGoles = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			cantidadDeGoles += it.next().getGolesDePenal();
		}
	
		return cantidadDeGoles;
	}

	public Integer getCantidadDeAmonestaciones() {
		Integer amonestaciones = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getAmonestado()) amonestaciones ++;
		}
	
		return amonestaciones;
	}

	public Integer getCantidadDeExpulsiones() {
		Integer expulsiones = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getExpulsado()) expulsiones ++;
		}
	
		return expulsiones;
	}

	public Integer getCantidadDeFiguras() {
		Integer figuras = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getFigura()) figuras ++;
		}
	
		return figuras;
	}

	public Integer getCantidadDeChenemigos() {
		Integer chenemigos = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getChenemigo()) chenemigos ++;
		}
	
		return chenemigos;
	}

	public Integer getCantidadDeVallasInvictas() {
		Integer vallasInvictas = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getGolesRecibidos() == 0) vallasInvictas ++;
		}
	
		return vallasInvictas;
	}

	public Integer getCantidadDePartidosJugados() {
		Integer cantidadDePartidosJugados = 0;
		Iterator<DatosDeUnaFecha> it = this.datosDelasFechas.iterator();

		while (it.hasNext()) {
			if (it.next().getJugo()) cantidadDePartidosJugados ++;
		}
	
		return cantidadDePartidosJugados;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	 public boolean equals(java.lang.Object other) {
		if (other == null) return false
		if (this.is(other)) return true
		if (!(other instanceof Jugador)) return false
		if (!other.canEqual(this)) return false
		if (this.getId() != other.getId()) return false
		return true
	 }
		
}
