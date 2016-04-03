package workspacegrails

class DatosGenerales {

	public static Integer getMaximoEnteroPosible() {
		Long enteroMaximo =  (long)(Math.pow(2, (Integer.SIZE - 1) ) - 1);
		return enteroMaximo.intValue();
	}

	public static Integer getMinimoEnteroPosible() {
		return (getMaximoEnteroPosible() * -1);
	}

}
