package workspacegrails

import grails.transaction.Transactional

@Transactional
class UsuarioService extends GuardadoService{
	def coleccionesService;

	@Override
	public Boolean guardar(def unUsuario) {
		if (unUsuario.save(flush: true)) return true;
		return false;
	}

	@Override
	public Boolean eliminar(def unUsuario) {
		def equipos = unUsuario.getEquipos();
		coleccionesService.eliminarObjetosDeColeccion(equipos);
		unUsuario.delete(flush: true);
		if (unUsuario.hasErrors()) return false;
		return true;
	}

}
