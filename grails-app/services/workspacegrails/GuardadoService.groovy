package workspacegrails

import grails.transaction.Transactional

@Transactional
class GuardadoService {

	public Boolean guardar(def unObjeto) {
		if (unObjeto.save()) return true;
		return false;
	}

	public Boolean eliminar(def unObjeto) {
		unObjeto.delete();
		if (!unObjeto) return true;
		return false;
	}

}
