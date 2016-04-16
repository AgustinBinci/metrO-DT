package workspacegrails

public class ValidadorDeContraseniaService {

	def validadorDeNuevaContraseniaService;

	public List<String> getErroresDeValidacion(def params) {
		def contrasenias = [contrasenia: params.nuevaContrasenia, contraseniaRepetida: params.nuevaContraseniaRepetida];

		List<String> errores = validadorDeNuevaContraseniaService.getErroresDeValidacion(contrasenias);

		if (params.contraseniaActual) {

			//Contraseña actual
			if (params.contraseniaActual.isEmpty()) errores.add("Se debe ingresar la contraseña actual");
			else {
				if (params.contraseniaActual.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("La contraseña actual no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else if (params.contraseniaEncriptada != params.contraseniaActualEncriptada)
					errores.add("La contraseña actual no coincide");
			}

		}
		else errores.add("Se debe ingresar la contraseña actual");

		return errores;
	}
}
