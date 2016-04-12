package workspacegrails

import grails.transaction.Transactional

class UsuarioController extends GeneralController{

	static transactional = true;

	def encriptadorService;
	def usuarioService;
	def guardadoService;
	def modelMapper;
	def validadorDeUsuarioService;
	def ValidadorDeContraseniaService;

	def index() {
		try {
			def usuarios = Usuario.list();
			List<UsuarioDTO> usuariosIndex = new ArrayList<>();

			for (Usuario unUsuario : usuarios) {
				//Mapeo usuario
				UsuarioDTO unUsuarioIndex = modelMapper.map(unUsuario, UsuarioDTO.class);

				usuariosIndex.add(unUsuarioIndex);
			}
			return render(view:'index', model:[usuarios:usuariosIndex]);
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def login() {
		try {
			if (params.email && params.contrasenia) {
				Usuario unUsuario = Usuario.findByEmailAndContrasenia(params.email, encriptadorService.getSha256De(params.contrasenia));	
				if (unUsuario) {
					session.user = unUsuario;
					return chain(controller: "home", action: "index");
				}
				else {
					flash.errorMessage = "El usuario y contrasenia ingresados no coinciden con ningun usuario registrado";			
					return render(view: "login", model: [email: params.email]);
				}
			}
			else {
				if (!flash.message && !flash.errorMessage) flash.message = "Ingrese sus datos";
				return render(view: "login");
			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def logout() {
		try {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (unUsuario) {
				session.user = null;
				return redirect(action: "login");
			}
			else return chain(controller: "home", action: "index");
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def create() {
		try {
			//GET
			if (request.method == "GET") {
				flash.message = "Ingresa tus datos";
				return render(view: "create", model: []);	
			}

			//POST
			else {
				def parametros = [nombre: params.nombre, apellido: params.apellido, email: params.email, nombreDelEquipo: params.nombreDelEquipo];
				def mensajes = [:];

				List<String> errores = validadorDeUsuarioService.getErroresDeValidacion(params);

				//Chequeo email repetido
				Usuario usuarioExistente = Usuario.findByEmail(params.email);
				if (usuarioExistente) errores.add("Ya existe un usuario con ese email");

				if (errores.isEmpty()) {
					this.crearYguardarUsuario(params);
					return chain(action: "login", params: mensajes);
				}
				else {
					for (String unError : errores) {
						if (!flash.errorMessage) flash.errorMessage = unError;
						else flash.errorMessage += ", " + unError;
					}
					return render(view: "create", model: parametros);	
				}
			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def show() {
		try {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (unUsuario) {
				//Mapeo usuario
				UsuarioDTO unUsuarioEdit = modelMapper.map(unUsuario, UsuarioDTOextendido.class);

				return render(view:'show', model: [usuario: unUsuarioEdit]);
			}
			else {
				flash.errorMessage = "Error inesperado";
				return chain(controller: "error", action: "error");	
			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	
	def cambiarContrasenia() {
		try {
			//GET
			if (request.method == "GET") return render(view: "cambiarContrasenia", model: []);

			//POST
			else {
				Usuario unUsuario = Usuario.findById(session.user.getId());
				params.contraseniaEncriptada = unUsuario.getContrasenia();
				params.contraseniaActualEncriptada = encriptadorService.getSha256De(params.contraseniaActual);

				List<String> errores = validadorDeContraseniaService.getErroresDeValidacion(params);

				if (errores.isEmpty()) {
					unUsuario.setContrasenia(encriptadorService.getSha256De(params.nuevaContrasenia));

					if (usuarioService.guardar(unUsuario)) flash.message = "Contraseña actualizada correctamente";
					else flash.errorMessage = "Error al cambiar la contraseña";

					return chain(action: "show");
				}
				else {
					for (String unError : errores) {
						if (!flash.errorMessage) flash.errorMessage = unError;
						else flash.errorMessage += ", " + unError;
					}

					return render(view: "cambiarContrasenia", model: []);
				}

			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def edit() {
		try {
			Usuario unUsuario = Usuario.findById(session.user.getId());
			if (unUsuario) {
				//Mapero usuario
				UsuarioDTO unUsuarioEdit = modelMapper.map(unUsuario, UsuarioDTOextendido.class);

				//GET
				if (request.method == "GET") return render(view:'edit', model:[usuario:unUsuarioEdit]);

				//POST
				else {
					List<String> errores = validadorDeUsuarioService.getErroresDeValidacion(params);

					if (unUsuario.getEmail() != params.email && Usuario.findByEmail(params.email)) errores.add("Ya existe un usuario con ese email");

					if (errores.isEmpty()) {
						if (usuarioService.guardar(unUsuario)) flash.message = "Usuario actualizado correctamente";
						else flash.errorMessage = "Error al actualizar el usuario";
						return chain(action: "show");
					}
					else {
						for (String unError : errores) {
							if (!flash.errorMessage) flash.errorMessage = unError;
							else flash.errorMessage += ", " + unError;
						}
						return render(view: 'edit', model: [usuario: unUsuarioEdit]);
					}
				}

			}
			else {
				flash.errorMessage = "Error inesperado";
				return chain(controller: "error", action: "error");
			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	def showEquipo() {
		try {
			if (params.id) {
				Usuario unUsuario = Usuario.findById(params.id);
				EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
				if (unEquipo) return render(view: "/EquipoDeUnaFecha/show", model: [])
				else {
					flash.errorMessage = "No hay equipo";
					return chain(action: "index");
				}
			}
			else return chain(action: "index");
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	@Transactional
	def delete() {
		try {
			if (params.id) {
				Usuario unUsuario = Usuario.findById(params.id);
				if (unUsuario) {
					if( (session.user.getId() == unUsuario.getId()) || (session.user.getAdmin()) ) {
						if(session.user.getId() == unUsuario.getId()) session.user = null;
						if (usuarioService.eliminar(unUsuario)) {
							flash.message = "Usuario eliminado correctamente";
							if (session.user) return chain(action: "index");
							else return chain(controller: "home", action: "index");
						}
						else {
							flash.errorMessage = "Error al eliminar el usuario";
							return chain(action: "index");
						}
					}
					else {
						flash.errorMessage = "No tenes los permisos para eliminar este usuario";
						return chain(action: "index");
					}
				}
				else {
					flash.errorMessage = "No existe ningun usuario con ese identificador";
					return chain(action: "index");
				}
			}
			else return chain(action: "index");
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	@Transactional
	private def crearYguardarUsuario(def params) {
		try {
			//Instancio opciones del juego
			OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.getAll().get(0);

			//Creo el usuario
			Usuario unUsuario = new Usuario(nombre: params.nombre, apellido: params.apellido, contrasenia: encriptadorService.getSha256De(params.contrasenia),
										email: params.email, nombreDelEquipo: params.nombreDelEquipo, admin: false,
										presupuesto: opcionesDelJuego.getPresupuestoInicial(), cambiosGranDt: opcionesDelJuego.getCambiosGranDtIniciales(), 												cambiosInternos: opcionesDelJuego.getCambiosInternosIniciales(),
										cambiosDeSuplente: opcionesDelJuego.getCambiosDeSuplenteIniciales());

			//Formacion y equipo
			Formacion unaFormacion = Formacion.findByCantidadDeDefensoresAndCantidadDeVolantesAndCantidadDeDelanteros(4, 4, 2);
			EquipoDeUnaFecha unEquipo = new EquipoDeUnaFecha(fecha: FechaActual.first().getNumeroDeFecha(), formacion: unaFormacion);

			if (guardadoService.guardar(unEquipo)) {
				unUsuario.addToEquipos(unEquipo);
				if (usuarioService.guardar(unUsuario)) {
					Torneo torneoPrincipal = Torneo.findByTorneoPrincipal(true);
					if (torneoPrincipal) {
						//torneoPrincipal.addToParticipantes(unUsuario);
						//guardadoService.guardar(torneoPrincipal);
					}
					flash.message = "Usuario creado!";
				}
				else flash.errorMessage = "Error al crear usuario";

			}
			else flash.errorMessage = "Error al crear el equipo inicial";
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
		}
	}
 
}
