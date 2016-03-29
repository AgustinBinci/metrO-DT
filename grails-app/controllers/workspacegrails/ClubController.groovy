package workspacegrails

class ClubController {

	def index() {
		def clubes = Club.list();
		List<ClubIndex> clubesIndex = new ArrayList<>();

		for (Club unClub : clubes) {
			ClubIndex unClubIndex = new ClubIndex();
			unClubIndex.setNombre(unClub.getNombre());
			unClubIndex.setId(unClub.getId());
			clubesIndex.add(unClubIndex);
		}

		return render(view:'index', model:[clubes:clubesIndex]);
	}

	def create() {
		if ( session.user && session.user.getAdmin() ){
			if (params.nombre) {
				Club unClub = new Club(nombre: params.nombre);			
				if (unClub.save()) flash.message = "Club creado!";
				else flash.errorMessage = "Error al crear club";
				return redirect(action:"show", id: unClub.id);
			}
			else return render(view: "create", model:[]);
		}
		else {
			flash.errorMessage = "No tenes permisos para crear un club!";
			return render(view: "index");
		}
	}

	def show() {
		if (params.id) {
			Club unClub = Club.findById(params.id);
			if (unClub) {
				ClubIndex unClubIndex = new ClubIndex();
				unClubIndex.setNombre(unClub.getNombre());
				unClubIndex.setId(unClub.getId());
				return render(view:'show', model:[club: unClubIndex, jugadores: unClub.getJugadores()]);
			}
			else {
				flash.errorMessage = "No existe ningun club con ese identificador";
				return redirect(action:"index");			
			}
		}
		else {
			return render(view: "index");
		}
	}

	def edit() {
		if ( session.user && session.user.getAdmin() ){
			if (params.id) {
				Club unClub = Club.findById(params.id);
				if (unClub) {
					if (params.nombre) {
						unClub.setNombre(params.nombre);		
						if (unClub.save()) flash.message = "Club editado correctamente";
						else flash.errorMessage = "Error al editar club";
						return redirect(action:"show", id: unClub.id);
					}
					else {
						ClubIndex unClubIndex = new ClubIndex();
						unClubIndex.setNombre(unClub.getNombre());
						unClubIndex.setId(unClub.getId());
						return render(view: "edit", model: [club: unClubIndex]);
					}
				}
				else return redirect(action:"index");
			}
			else return redirect(action:"index");
		}
		else {
			flash.errorMessage = "No tenes permisos para editar un club!";
			return render(view: "index");
		}
	}

	def delete() {
		if ( session.user && session.user.getAdmin() ){
			if (params.id) {
				Club unClub = Club.findById(params.id);
				if (unClub) {
					unClub.delete();
					flash.message = "Club eliminado correctamente";
				}
				else flash.errorMessage = "No existe ningun club con ese identificador";
			}
			return redirect(action:"index");
		}
		else {
			flash.errorMessage = "No tenes permisos para eliminar un club!";
			return render(view: "index");
		}
	}

}
