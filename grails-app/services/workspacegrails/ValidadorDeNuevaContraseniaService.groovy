package workspacegrails

public class ValidadorDeNuevaContraseniaService  {

	public List<String> getErroresDeValidacion(def params) {
		List<String> errores = new ArrayList<String>();

		if (params.contrasenia && params.contraseniaRepetida) {

			//Nueva contraseña
			if (params.contrasenia.isEmpty()) errores.add("Se debe ingresar una nueva contraseña");
			else {
				if (params.contrasenia.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("La nueva contraseña no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else {
					if (params.contraseniaRepetida.isEmpty()) errores.add("Se debe confirmar la nueva contraseña");
					else {
						if (params.contraseniaRepetida.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
							errores.add("La confirmación de la nueva contraseña no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
						else if (params.contrasenia != params.contraseniaRepetida) errores.add("Las contraseñas ingresadas no coinciden");
					}
				}
			}

		}
		else errores.add("Se deben completar todos los campos");

		return errores;
	}

}
