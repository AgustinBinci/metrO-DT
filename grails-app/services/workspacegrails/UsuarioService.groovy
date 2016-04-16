package workspacegrails

import grails.transaction.Transactional;
import workspacegrails.Servicio;

@Transactional
class UsuarioService extends Servicio {
	def coleccionesService;
	def equipoDeUnaFechaService;
	def encriptadorService;

	@Override
	public void setAtributos(def usuario, def params) {
		try {
			if (params) {
				if (params.nombre) usuario.setNombre(params.nombre);
				if (params.apellido) usuario.setApellido(params.apellido);
				if (params.contrasenia) usuario.setContrasenia(encriptadorService.getSha256De(params.contrasenia));
				if (params.email) usuario.setEmail(params.email);
				if (params.nombre) usuario.setNombre(params.nombre);			
				if (params.nombreDelEquipo) usuario.setNombreDelEquipo(params.nombreDelEquipo);		
			}
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	@Override
	public Boolean guardar(def unUsuario) {
		try {
			for (EquipoDeUnaFecha unEquipo : unUsuario.getEquipos()) {
				if (!equipoDeUnaFechaService.guardar(unEquipo)) return false;
			}

			//TODO: agregar el usuario al torneo
			if (unUsuario.save(flush: true)) return true;

			return false;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	@Override
	public Boolean eliminar(def unUsuario) {
		try {
			def equipos = unUsuario.getEquipos();
			coleccionesService.eliminarObjetosDeColeccion(equipos);
			unUsuario.delete(flush: true);
			if (unUsuario.hasErrors()) return false;
			return true;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	@Override
	public def crear() {
		try {
			Usuario usuario = new Usuario();

			//Instancio opciones del juego
			OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.getAll().get(0);

			usuario.setAdmin(false);
			usuario.setPresupuesto(OpcionesDelJuego.getPresupuestoInicial());
			usuario.setCambiosGranDt(OpcionesDelJuego.getCambiosGranDtIniciales());
			usuario.setCambiosInternos(OpcionesDelJuego.getCambiosInternosIniciales());
			usuario.setCambiosDeSuplente(OpcionesDelJuego.getCambiosDeSuplenteIniciales());

			EquipoDeUnaFecha equipoDeUnaFecha = equipoDeUnaFechaService.crear();
			usuario.addToEquipos(equipoDeUnaFecha);

			return usuario;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

}
