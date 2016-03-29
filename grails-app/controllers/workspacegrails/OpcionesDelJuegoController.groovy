package workspacegrails

class OpcionesDelJuegoController {

	def index() {
		return redirect(action: "show");
	}

	def show() {
		OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.first();
		Liga torneoPrincipal = Liga.findByTorneoPrincipal(true);
		if (opcionesDelJuego) return render(view: "show", model: [opcionesDelJuego: opcionesDelJuego, torneoPrincipal: torneoPrincipal]);
		else return redirect(uri: "/");
	}

	def edit() {
		if (session.user && session.user.getAdmin()) {
			OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.first();
			if (opcionesDelJuego) {
			
				//Si hay parametros
				if (params.presupuestoInicial && params.cambiosInternosIniciales && params.cambiosGranDtIniciales && params.cambiosDeSuplenteIniciales && 						params.jugadoresDeUnMismoClubPermitidos) {
					opcionesDelJuego.setPresupuestoInicial(Integer.parseInt(params.presupuestoInicial));
					opcionesDelJuego.setCambiosInternosIniciales(Integer.parseInt(params.cambiosInternosIniciales));
					opcionesDelJuego.setCambiosGranDtIniciales(Integer.parseInt(params.cambiosGranDtIniciales));
					opcionesDelJuego.setCambiosDeSuplenteIniciales(Integer.parseInt(params.cambiosDeSuplenteIniciales));
					opcionesDelJuego.setJugadoresDeUnMismoClubPermitidos(Integer.parseInt(params.jugadoresDeUnMismoClubPermitidos));
					if (opcionesDelJuego.save()) flash.message = "Opciones generales actualizadas correctamente";
					else flash.errorMessage = "Error al actualizar las opciones generales";
					return redirect (action: "show");
				}
				else return render(view: "edit", model: [opcionesDelJuego: opcionesDelJuego]);

			}
			else {
				return redirect(uri: "/");
			}
		}
		else {
			flash.errorMessage = "No tenes permisos para editar las opciones generales!";
			return redirect(uri: "/");
		}
	}
  
}
