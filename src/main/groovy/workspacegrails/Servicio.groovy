package workspacegrails

import grails.transaction.Transactional

abstract class Servicio {

	@Transactional
	public Boolean guardar(def unObjeto) {
		if (unObjeto.save()) return true;
		return false;
	}

	@Transactional
	public Boolean eliminar(def unObjeto) {
		unObjeto.delete();
		if (!unObjeto) return true;
		return false;
	}

	public abstract def crear(def params);

}
