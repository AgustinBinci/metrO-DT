package workspacegrails

class GeneralController {

	def finalizarFecha() {
		if (session.user && session.user.getAdmin()) {
			FechaActual fechaActual = FechaActual.first();

			if (fechaActual.comenzoLaFecha()) {
				return;
			}
			else {
				flash.errorMessage = "No podes finalizar una fecha que nunca empezo!";
				return redirect(action: "show");
			}	
		}
		else {
			flash.errorMessage = "No tenes permisos para editar la fecha!";
			return render(view: "index");
		}
	}
}
