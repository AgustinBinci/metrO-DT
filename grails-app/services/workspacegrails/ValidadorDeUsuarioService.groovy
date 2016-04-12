package workspacegrails

class ValidadorDeUsuarioService {

	public List<String> getErroresDeValidacion(def params) {
		List<String> errores = new ArrayList<String>();

		if (params.nombre && params.apellido && params.contrasenia && params.contraseniaRepetida && params.email && params.nombreDelEquipo) {

			//Nombre
			if (params.nombre.isEmpty()) errores.add("El nombre no puede estar vacío");
			else if (params.nombre.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
				errores.add("El nombre no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");

			//Apellido
			if (params.apellido.isEmpty()) errores.add("El apellido no puede estar vacío");
			else if (params.apellido.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
				errores.add("El apellido no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");

			//Contraseña
			if (params.contrasenia.isEmpty()) errores.add("La contraseña no puede estar vacía");
			else {
				if (params.contrasenia.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("La contraseña no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else {
					if (params.contraseniaRepetida.isEmpty() || params.contraseniaRepetida.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
						errores.add("Las contraseñas no coinciden");
					else {
						//Coincidencia de contraseñas
						if (params.contrasenia != params.contraseniaRepetida) errores.add("Las contraseñas ingresadas no coinciden");
					}
				}
			}

			//Email
			if (params.email.isEmpty()) errores.add("El email no puede estar vacío");
			else {
				if (params.email.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
					errores.add("El email no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
				else {
					//TODO: Expresion regular

				}
			}

			//Nombre del equipo
			if (params.nombreDelEquipo.isEmpty()) errores.add("El nombre del equipo no puede estar vacío");
			else if (params.nombreDelEquipo.length() > DatosGenerales.getLongitudMaximaDeUnStringBasico())
				errores.add("El nombre del equipo no puede tener mas de " + DatosGenerales.getLongitudMaximaDeUnStringBasico().toString() + " caracteres");
		
		}
		else errores.add("Se deben llenar todos los campos obligatorios");

		return errores;
	}

}
