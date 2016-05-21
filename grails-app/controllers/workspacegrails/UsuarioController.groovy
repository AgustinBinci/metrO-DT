package workspacegrails

import grails.transaction.Transactional

class UsuarioController extends GeneralController {

	static transactional = true;

	def encriptadorService;
	def usuarioService;
	def equipoDeUnaFechaService;
	def modelMapper;
	def validadorDeUsuarioService;
	def validadorDeNuevaContraseniaService;
	def validadorDeContraseniaService;
	def generadorDeStringsService;
	def datosGeneralesService;
	def peticionDeCambioDeContraseniaService;
	def mailService;

	def index() {
		try {
			def usuarios = Usuario.findAllWhere(activo: true);
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
				Usuario unUsuario = Usuario.findByEmailAndContraseniaAndActivo(params.email, encriptadorService.getSha256De(params.contrasenia), true);	
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
				errores.addAll(validadorDeNuevaContraseniaService.getErroresDeValidacion(params));

				//Chequeo email repetido
				Usuario usuarioExistente = Usuario.findByEmailAndActive(params.email, true);
				if (usuarioExistente) errores.add("Ya existe un usuario con ese email");

				if (errores.isEmpty()) {
					Usuario unUsuario = usuarioService.crear();
					usuarioService.setAtributos(unUsuario, params);

					if (usuarioService.guardar(unUsuario)) flash.message = "Usuario creado correctamente";
					else flash.errorMessage = "Error al crear el usuario";

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

	def regenerarContrasenia() {
		try {
			if (params.enlace) {
				 PeticionDeCambioDeContrasenia unaPeticion = PeticionDeCambioDeContrasenia.findByEnlace(params.enlace);
				 
				if (unaPeticion) {
					//Genero contraseña
					String unaContrasenia = generadorDeStringsService.generarString(datosGeneralesService.getTamanioStandarDeLaContrasenia());
	
					//Guardo contraseña encriptada
					unaPeticion.getUsuario().setContrasenia(encriptadorService.getSha256De(unaContrasenia));

					//Elimino peticion
					peticionDeCambioDeContraseniaService.eliminar(unaPeticion);

					//TODO: Enviar email con la contraseña
			
					flash.message = "Tu contraseña fué actualizada automáticamente, te llegará un mail con la nueva contraseña";
				}
				else flash.errorMessage = "El enlace no existe";
		
			}
			else {
				if (params.email) {
					Usuario unUsuario = Usuario.findByEmail(params.email);

					if (unUsuario) {
						PeticionDeCambioDeContrasenia unaPeticion = peticionDeCambioDeContraseniaService.crear();
						peticionDeCambioDeContraseniaService.setAtributos(unaPeticion, [usuario: unUsuario, enlace: generadorDeStringsService.generarString(datosGeneralesService.getTamanioDelEnlace())]);
						
						//TODO: Enviar email con el enlace

						flash.message = "Tu petición fue procesada, te llegará un mail con un enlace para recuperar tu contraseña";
					}
					else flash.errorMessage = "No hay ningun usuario con ese email";
				}
				else return render(view: "regenerarContrasenia", model: []);
			}
			return chain(action: "login");
			
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
					usuarioService.setAtributos(unUsuario, [contrasenia: params.nuevaContrasenia]);

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

					if (unUsuario.getEmail() != params.email && Usuario.findByEmailAndActivo(params.email, true)) errores.add("Ya existe un usuario con ese email");

					if (errores.isEmpty()) {
						usuarioService.setAtributos(unUsuario, params);

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
			Usuario unUsuario = this.getUserToDelete(params);
			if (unUsuario) {
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
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	@Transactional
	def deleteLogico() {
		try {
			Usuario unUsuario = this.getUserToDelete(params);
			if (unUsuario) {
				if (usuarioService.eliminarLogicamente(unUsuario)) {
					flash.message = "Usuario eliminado correctamente";
					if (session.user) return chain(action: "index");
					else return chain(controller: "home", action: "index");
				}
				else {
					flash.errorMessage = "Error al eliminar el usuario";
					return chain(action: "index");
				}
			}
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			return chain(controller: "error", action: "error");
		}
	}

	@Transactional
	private Usuario getUserToDelete(def params) {
		try {
			if (params.id) {
				Usuario unUsuario = Usuario.findById(params.id);
				if (unUsuario) {
					if( (session.user.getId() == unUsuario.getId()) || (session.user.getAdmin()) ) {
						if(session.user.getId() == unUsuario.getId()) session.user = null;
						return unUsuario;
					}
					else {
						flash.errorMessage = "No tenes los permisos para eliminar este usuario";
						chain(action: "index");
					}
				}
				else {
					flash.errorMessage = "No existe ningun usuario con ese identificador";
					chain(action: "index");
				}
			}
			else chain(action: "index");
			return null;
		}
		catch(Exception unaExcepcion) {
			flash.errorMessage = unaExcepcion.getMessage();
			chain(controller: "error", action: "error");
			return null;
		}
	}
 
}
