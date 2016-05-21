package workspacegrails

import grails.transaction.Transactional;
import workspacegrails.Servicio;

@Transactional
class PeticionDeCambioDeContraseniaService extends Servicio {

	@Override
	public Boolean eliminar(def unaPeticion) {
		try {
			unaPeticion.setUsuario(null);
			unaPeticion.delete();

			if (unaPeticion.hasErrors()) return false;
			return true;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	@Override
	public void setAtributos(def unaPeticion, def params) {
		if (params.usuario) {
			Usuario unUsuario = (Usuario)(params.usuario);
			unaPeticion.setUsuario(unUsuario);
		}
		if (params.enlace) unaPeticion.setEnlace(params.enlace);
	}

	@Override
	public def crear() {
		return (new PeticionDeCambioDeContrasenia());
	}

}
