package workspacegrails

class GeneradorDeStringsService {

	public String generarString(Integer caracteresMaximos) {
		String unString = "";
		Integer unRandom;
		def caracteresPosibles = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'];

		for (Integer i = 0; i < caracteresMaximos; i++) {
			unRandom = (int) Math.round(Math.random() * caracteresPosibles.size()) ;
			unString += caracteresPosibles[unRandom];
		}

		return unString;
	}

}
