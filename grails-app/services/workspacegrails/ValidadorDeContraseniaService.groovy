package workspacegrails

public class ValidadorDeContraseniaService {

	public List<String> getErroresDeValidacion(def params) {
		List<String> errores = new ArrayList<String>();

		if (params.contraseniaActual && params.nuevaContrasenia && params.nuevaContraseniaRepetida) {

			//Contraseña actual
			if (params.contraseniaActual.isEmpty()) errores.add("Se debe ingresar la contraseña actual");
			else {
				if (params.contraseniaActual.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("La contraseña actual no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else if (params.contraseniaEncriptada != params.contraseniaActualEncriptada)
					errores.add("La contraseña actual no coincide");
			}

			//Nueva contraseña
			if (params.nuevaContrasenia.isEmpty()) errores.add("Se debe ingresar una nueva contraseña");
			else {
				if (params.nuevaContrasenia.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("La nueva contraseña no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else {
					if (params.nuevaContraseniaRepetida.isEmpty()) errores.add("Se debe confirmar la nueva contraseña");
					else {
						if (params.nuevaContraseniaRepetida.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
							errores.add("La confirmación de la nueva contraseña no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
						else if (params.nuevaContrasenia != params.nuevaContraseniaRepetida) errores.add("Las contraseñas ingresadas no coinciden");
					}
				}
			}

		}
		else errores.add("Se deben completar todos los campos");

		return errores;
	}
}
