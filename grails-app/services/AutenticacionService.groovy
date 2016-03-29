package workspacegrails


class AutenticacionService {

	Boolean autenticar(String unEmail, String unaContrasenia) {
		Usuario unUsuario = Usuario.findByEmailAndContrasenia(unEmail, unaContrasenia);
		
		if (unUsuario) {
			session.user = unUsuario;
			return true;
		}
		else return false;
	}

}
