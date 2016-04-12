package workspacegrails

class ErrorController {

	def error() {
		/*if(!session.user) {
			flash.errorMessage = "Debes ingresar para acceder a esa pagina";
			return chain(controller: "usuario", action: "login");
		}
		else */return render(view: "error");
	}

	def notFound() {
		/*if(!session.user) {
			flash.errorMessage = "Debes ingresar para acceder a esa pagina";
			return chain(controller: "usuario", action: "login");
		}
		else */return render(view: "notFound");
	}

}
