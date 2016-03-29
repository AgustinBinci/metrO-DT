package workspacegrails

import grails.transaction.Transactional

@Transactional
class LigaController {

	def coleccionesService;

	def index() {
		return redirect(uri: "/");
	}

	def show() {
		if (session.user) {
			if (params.id) {
				Liga unTorneo = Liga.findById(params.id);
				List<Integer> fechas = unTorneo.getFechas();
				unTorneo.actualizarTorneo();
				unTorneo.save();
				Collections.sort(fechas);
				if (unTorneo) return render(view: "show", model: [torneo: unTorneo, fechas: fechas]);
				else {
					flash.errorMessage = "No existe torneo con ese identificador";
					return render(view: "index");
				}
			}
			else return render(view: "index");
		}
		else return render(view: "index");
	}

	def showTorneoPrincipal() {
		if (session.user) {
			Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);
			torneoPrincipal.actualizarTorneo();
			torneoPrincipal.save();
			List<Integer> fechas = torneoPrincipal.getFechas();
			Collections.sort(fechas);
			if (torneoPrincipal) return render(view: "show", model: [torneo: torneoPrincipal, fechas: fechas]);
		}
		else return render(view: "index");
	}

}
