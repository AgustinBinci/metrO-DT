package workspacegrails

class Aleatoria extends CriterioDeSeleccion{

    static constraints = {
    }

	public def getParticipantesSeleccionados(def participantes, Integer porcentajeDeParticipantes) {
		Integer total = participantes.size();
		Integer random;
		Integer porcentajeParcial;
		Integer agregados = 0;
		Iterator it = participantes.iterator();
		def participantesSeleccionados;
		Boolean cupoCompleto = false;
		Boolean agregado = false;

		while (it.hasNext() && !cupoCompleto) {
			porcentajeParcial = (agregados + 1) * 100 / total;
			if ((porcentajeParcial <= porcentajeDeParticipantes) && (participantesSeleccionados.size() < participantes.size())) {
				agregado = false;

				while (!agregado) {
					random = Random.nextInt() * total;
					Usuario unParticipante = participantes.get(random);
					agregado = participantesSeleccionados.contains(unParticipante);
				}

				participantesSeleccionados.add(unParticipante);
				agregados ++;
			}
			else cupoCompleto = true;
		}

		return participantesSeleccionados;
	}

	@Override
	public String toString() {
		return "Seleccion aleatoria";
	}

}
