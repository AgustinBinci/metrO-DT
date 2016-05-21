import workspacegrails.*

class BootStrap {

	def encriptadorService;
	def mailService;

    def init = { servletContext ->
	
	//Prueba email
	/*mailService.sendMail {
	   to "agustinbinci@gmail.com"
	   subject "Prueba"
	   text 'Prueba enviando el mail'
	}*/

	//Posiciones	
	(new Arquero()).save(failOnError: true);
	(new Defensor()).save(failOnError: true);
	Volante unVolante = new Volante();
	unVolante.save(failOnError: true);
	(new Delantero()).save(failOnError: true);

	//Formaciones
	(new Formacion(cantidadDeDefensores: 5, cantidadDeVolantes: 4, cantidadDeDelanteros: 1)).save(failOnError: true);
	(new Formacion(cantidadDeDefensores: 5, cantidadDeVolantes: 3, cantidadDeDelanteros: 2)).save(failOnError: true);
	(new Formacion(cantidadDeDefensores: 4, cantidadDeVolantes: 5, cantidadDeDelanteros: 1)).save(failOnError: true);
	Formacion unaFormacion = new Formacion(cantidadDeDefensores: 4, cantidadDeVolantes: 4, cantidadDeDelanteros: 2);
	unaFormacion.save(failOnError: true);
	(new Formacion(cantidadDeDefensores: 4, cantidadDeVolantes: 3, cantidadDeDelanteros: 3)).save(failOnError: true);
	(new Formacion(cantidadDeDefensores: 3, cantidadDeVolantes: 5, cantidadDeDelanteros: 2)).save(failOnError: true);
	(new Formacion(cantidadDeDefensores: 3, cantidadDeVolantes: 4, cantidadDeDelanteros: 3)).save(failOnError: true);

	//OpcionesDelJuego
	OpcionesDelJuego opcionesDelJuego = new OpcionesDelJuego();
	opcionesDelJuego.save(failOnError: true);

	//Fecha actual
	Calendar fechaDeInicio = GregorianCalendar.getInstance();
	fechaDeInicio.add(Calendar.YEAR, 1);

	FechaActual unaFecha = new FechaActual(numeroDeFecha: 1, fechaFinalizada: false, fechaIniciada: false, fechaDeInicio: fechaDeInicio);
	unaFecha.save(failOnError: true);


	//Clubes
	Club unClub = new Club(nombre: "Boca Juniors");
	Club otroClub = new Club(nombre: "Riber");
	unClub.save(failOnError: true);
	otroClub.save(failOnError: true);

	//Jugadores
	Jugador unJugador = new Jugador(nombre: "Colazo", precio: 550000, posicion: unVolante, club: unClub);
	Jugador otroJugador = new Jugador(nombre: "Alario", precio: 1200000, posicion: unVolante, club: otroClub);
	unJugador.save(failOnError: true);
	otroJugador.save(failOnError: true);
	unClub.save(failOnError: true);
	otroClub.save(failOnError: true);

	//Equipos
	EquipoDeUnaFecha unEquipo = new EquipoDeUnaFecha(fecha: unaFecha.getNumeroDeFecha(), formacion: unaFormacion);
	EquipoDeUnaFecha otroEquipo = new EquipoDeUnaFecha(fecha: unaFecha.getNumeroDeFecha(), formacion: unaFormacion);
	unEquipo.save(failOnError: true);
	otroEquipo.save(failOnError: true);

	//Usuarios
	Usuario admin = new Usuario(nombre: "Agustin", apellido: "Binci", email: "agustin_attaque_77@hotmail.com", contrasenia: encriptadorService.getSha256De("1"), nombreDelEquipo: "Equipo de agustin", admin: true, presupuesto: opcionesDelJuego.getPresupuestoInicial(), cambiosGranDt: opcionesDelJuego.getCambiosGranDtIniciales(), cambiosInternos: opcionesDelJuego.getCambiosInternosIniciales(), cambiosDeSuplente: opcionesDelJuego.getCambiosDeSuplenteIniciales());
	admin.setActivo(true);
	admin.addToEquipos(unEquipo);
	admin.save(failOnError: true);

	Usuario user = new Usuario(nombre: "Pablo", apellido: "Saffer", email: "psg@hotmail.com", contrasenia: encriptadorService.getSha256De("1"), nombreDelEquipo: "Equipo de pablo", admin: false, presupuesto: opcionesDelJuego.getPresupuestoInicial(), cambiosGranDt: opcionesDelJuego.getCambiosGranDtIniciales(), cambiosInternos: opcionesDelJuego.getCambiosInternosIniciales(), cambiosDeSuplente: opcionesDelJuego.getCambiosDeSuplenteIniciales());
	user.setActivo(true);
	user.addToEquipos(otroEquipo);
	user.save(failOnError: true);

	//Criterios de seleccion
	//CriterioDeSeleccion random = new Aleatoria();
	//random.save(failOnError: true);

	//Cambios a eleccion
	CambioAeleccion cambioAeleccion = new CambioAeleccion(cambioInterno: true, cambioGranDt: false, cambioDeSuplentes: true);
	cambioAeleccion.save(failOnError: true);

	//Premios
	Premio premioPorFechaGanada = new Premio(descripcion: "Premio por fecha ganada", presupuestoAdicional: 200000);
	premioPorFechaGanada.addToCambiosAeleccion(cambioAeleccion);
	premioPorFechaGanada.save(failOnError: true);

	Premio premioPorTorneoPerdido = new Premio(descripcion: "Castigo por descender", presupuestoAdicional: -200000);
	premioPorTorneoPerdido.save(failOnError: true);

	//Tipos de torneo
	Liga liga = new Liga(nombre: "Torneo gape el trOme", fechaDeInicio: 1, fechaDeFin: 99999, porcentajeDeParticipantes: 100, torneoPrincipal: true, fechaActual: unaFecha, 
						premioPorFechaGanada: premioPorFechaGanada, premioPorTorneoPerdido: premioPorTorneoPerdido);
	//liga.addToParticipantes(admin);
	//liga.addToParticipantes(user);
	liga.save(failOnError: true);

    }

    def destroy = {
    }
}
