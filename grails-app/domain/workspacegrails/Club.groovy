package workspacegrails

import java.util.List;

class Club {

    static constraints = {
		jugadores(nullable: true);
		nombre(nullable: true, size: 1..50, blank: false);
    }

	static hasMany = [jugadores: Jugador];
	String nombre;

	@Override
	public String toString() {
		return this.nombre;
	}
}
