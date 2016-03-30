package workspacegrails


class GeneralInterceptor {

    GeneralInterceptor() {
	    match(controller: "fechaActual");
	    match(controller: "opcionesDelJuego");
	    match(controller: "club");
	    match(controller: "jugador");
	    match(controller: "equipoDeUnaFecha");
	    match(controller: "usuario", action: "edit");
	    match(controller: "usuario", action: "index");
	    match(controller: "usuario", action: "show");
	    match(controller: "usuario", action: "showEquipo");
	    match(controller: "usuario", action: "cambiarContrasenia");
	    match(controller: "usuario", action: "delete");
	    match(controller: "usuario", action: "guardarUsuario");
	    match(controller: "usuario", action: "logout");
    }

	protected Boolean validarSesion() {
        if(!session.user) {
		  flash.errorMessage = "Debes ingresar para acceder a esa pagina";
            chain(controller: "usuario", action: "login");
            return false;
        }
		return true;
	}

    boolean before() {
		this.validarSesion();
     }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
