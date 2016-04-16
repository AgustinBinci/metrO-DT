package workspacegrails

import grails.transaction.Transactional

abstract class Servicio {

	@Transactional
	public Boolean guardar(def unObjeto) {
		try {
			if (unObjeto.save()) return true;
			return false;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	@Transactional
	public Boolean eliminar(def unObjeto) {
		try{
			unObjeto.delete();
			if (!unObjeto) return true;
			return false;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

	public abstract void setAtributos(def unObjeto, def params);
	public abstract def crear();

}
