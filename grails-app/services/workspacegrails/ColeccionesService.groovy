package workspacegrails

import grails.transaction.Transactional

@Transactional
class ColeccionesService {

	Boolean sePuedeAgregarObjetoAlaColeccion(def unObjeto, def otroObjeto, def unComparador) {
		Iterator it = otroObjeto.iterator();

		while (it.hasNext()) {
			if (unComparador.comparate(unObjeto, it.next().getId()) == 0) return true;
		}
		
		return false;
	}

	void eliminarObjetosDeColeccion(def coleccion) {
		Iterator it = coleccion.iterator();
		while (it.hasNext()) {
			it.next().delete();
		}
	}

	void ordenarColeccion(def unObjeto, def otroObjeto, def unComparador) {
		Iterator it = otroObjeto.iterator();

		while (it.hasNext()) {
			if (unComparador.comparate(unObjeto, it.next().getId())) return;
		}
		
		return;
	}

}
