package workspacegrails

import grails.transaction.Transactional

class UsuarioController extends GeneralController{

	static transactional = true;
	def coleccionesService;
	def encriptadorService;

	def index() {
		def usuarios = Usuario.list();
		List<UsuarioIndex> usuariosIndex = new ArrayList<>();

		for (Usuario unUsuario : usuarios) {
			UsuarioIndex unUsuarioIndex = new UsuarioIndex();
			unUsuarioIndex.setId(unUsuario.getId());
			unUsuarioIndex.setNombre(unUsuario.getNombre());
			unUsuarioIndex.setApellido(unUsuario.getApellido());
			unUsuarioIndex.setNombreDelEquipo(unUsuario.getNombreDelEquipo());
			unUsuarioIndex.setAdmin(unUsuario.getAdmin());

			usuariosIndex.add(unUsuarioIndex);
		}
		return render(view:'index', model:[usuarios:usuariosIndex]);
	}

	def login() {
		if (params.email && params.contrasenia) {
			Usuario unUsuario = Usuario.findByEmailAndContrasenia(params.email, encriptadorService.getSha256De(params.contrasenia));	
			if (unUsuario) {
				session.user = unUsuario;
				return redirect(uri: "/");
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

	def logout() {
		if (params.id) {
			Usuario unUsuario = Usuario.findById(params.id);
			if (unUsuario) {
				if (unUsuario.getId() == session.user.getId()) {
					session.user = null;
					flash.message = "Adios ${unUsuario.getNombre()}";
					return redirect(action: "login");
				}
				else return redirect(uri:"/");
			}
			else return redirect(uri:"/");
		}
		else return redirect(uri:"/");
	}

	def create() {
		def parametros = [nombre: params.nombre, apellido: params.apellido, email: params.email, nombreDelEquipo: params.nombreDelEquipo];
		def mensajes = [:];

		if (params.nombre && params.apellido && params.contrasenia && params.email && params.nombreDelEquipo) {

			//Chequeo email repetido
			Usuario usuarioExistente = Usuario.findByEmail(params.email);

			if (!usuarioExistente) {
				//Instancio opciones del juego
				OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.getAll().get(0);

				//Creo el usuario
				Usuario unUsuario = new Usuario(nombre: params.nombre, apellido: params.apellido, contrasenia: encriptadorService.getSha256De(params.contrasenia),
											email: params.email, nombreDelEquipo: params.nombreDelEquipo, admin: false,
											presupuesto: opcionesDelJuego.getPresupuestoInicial(), cambiosGranDt: opcionesDelJuego.getCambiosGranDtIniciales(), 												cambiosInternos: opcionesDelJuego.getCambiosInternosIniciales(),
											cambiosDeSuplente: opcionesDelJuego.getCambiosDeSuplenteIniciales());
	
				//Guardo usuario
				this.guardarUsuario(unUsuario);
				return chain(action: "login", params: mensajes);		
			}
			else {
				parametros["email"] = null;
				flash.errorMessage = "Ya existe un usuario con ese email!";
				return render(view: "create", model: parametros);			
			}

		}
		else {
			flash.message = "Ingresa tus datos!";
			return render(view: "create", model: parametros);
		}
	}

	def show() {
		Usuario unUsuario = Usuario.findById(session.user.getId());
		if (unUsuario) {
			//Usuario edit
			UsuarioEdit unUsuarioEdit = new UsuarioEdit();
			unUsuarioEdit.setNombre(unUsuario.getNombre());
			unUsuarioEdit.setNombreDelEquipo(unUsuario.getNombreDelEquipo());
			unUsuarioEdit.setApellido(unUsuario.getApellido());
			unUsuarioEdit.setEmail(unUsuario.getEmail());
			return render(view:'show', model: [usuario: unUsuarioEdit]);
		}
		else {
			flash.errorMessage = "No existe ningun usuario con ese identificador";
			return redirect(action:"index");			
		}
	}

	
	def cambiarContrasenia() {
		def parametros = [:];
		if (params.contraseniaActual && params.nuevaContrasenia) {
			Usuario unUsuario = Usuario.findByIdAndContrasenia(session.user.getId(), encriptadorService.getSha256De(params.contraseniaActual));
			if (unUsuario) {
				unUsuario.setContrasenia(encriptadorService.getSha256De(params.nuevaContrasenia));
				if (unUsuario.save()) flash.message = "Contraseña actualizada correctamente";
				else flash.errorMessage = "Error al cambiar la contraseña";
				return redirect(action: "show");
			}
			else flash.errorMessage = "La contraseña actual no coincide";
		}
		else {
			parametros["contraseniaActual"] = params.contraseniaActual;
			parametros["nuevaContrasenia"] = params.nuevaContrasenia;
		}
		return render(view: "cambiarContrasenia", model: parametros);
	}

	def edit() {
		if (params.id) {
			Usuario unUsuario = Usuario.findById(params.id);
			if (unUsuario) {
				if (session.user && session.user.getId() == unUsuario.getId()) {
					if (params.nombre && params.apellido && params.email && params.nombreDelEquipo) {
						unUsuario.setNombre(params.nombre);
						unUsuario.setApellido(params.apellido);
						unUsuario.setEmail(params.email);
						unUsuario.setNombreDelEquipo(params.nombreDelEquipo);
	
						if (unUsuario.save()) flash.message = "Usuario editado correctamente";
						else flash.errorMessage = "Error al editar usuario";
						return redirect(action: "index");
					}
					else {
						//Creo usuario para la vista
						UsuarioEdit unUsuarioEdit = new UsuarioEdit();
						unUsuarioEdit.setId(unUsuario.getId());
						unUsuarioEdit.setNombre(unUsuario.getNombre());
						unUsuarioEdit.setApellido(unUsuario.getApellido());
						unUsuarioEdit.setNombreDelEquipo(unUsuario.getNombreDelEquipo());
						unUsuarioEdit.setEmail(unUsuario.getEmail());

						return render(view:'edit', model:[usuario:unUsuarioEdit]);
					}
				}
				else {
					flash.errorMessage = "No podes editar los datos de otros usuarios!";
					return redirect(action:"index");
				}
			}
			else {
				return redirect(action:"index");
			}
		}
		else {
			return redirect(action:"index");
		}
	}

	def showEquipo() {
		if (session.user) {
			if (params.id) {
				Usuario unUsuario = Usuario.findById(params.id);
				EquipoDeUnaFecha unEquipo = unUsuario.getEquipoDeLaFecha(FechaActual.first().getNumeroDeFecha());
				if (unEquipo) return render(view: "/EquipoDeUnaFecha/show", model: [])
				else {
					flash.errorMessage = "No hay equipo";
					return redirect (action: "index");
				}
			}
			else return redirect (action: "index");
		}
		else return redirect (action: "index");
	}

	def delete() {
		if (params.id) {
			Usuario unUsuario = Usuario.findById(params.id);
			if (unUsuario) {
				if (session.user) {
					if( (session.user.getId() == unUsuario.getId()) || (session.user.getAdmin()) ) {
						if(session.user.getId() == unUsuario.getId()) session.user = null;
						def equipos = unUsuario.getEquipos();
						coleccionesService.eliminarObjetosDeColeccion(equipos);
						unUsuario.delete();
						flash.message = "Usuario eliminado correctamente";
					}
				}
				
			}
			else flash.errorMessage = "No existe ningun usuario con ese identificador";
		}
		flash.errorMessage = "No tenes los permisos para eliminar este usuario";
		return redirect(action:"index");
	}

	@Transactional
	private def guardarUsuario(Usuario unUsuario) {
		try {
			Formacion unaFormacion = Formacion.findByCantidadDeDefensoresAndCantidadDeVolantesAndCantidadDeDelanteros(4, 4, 2);
			EquipoDeUnaFecha unEquipo = new EquipoDeUnaFecha(fecha: FechaActual.first().getNumeroDeFecha(), formacion: unaFormacion);

			if (unEquipo.save()) {
				unUsuario.addToEquipos(unEquipo);
				if (unUsuario.save(flush: true)) {
					Torneo torneoPrincipal = Torneo.findByTorneoPrincipal(true);
					if (torneoPrincipal) {
						torneoPrincipal.addToParticipantes(unUsuario);
						torneoPrincipal.save();
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
