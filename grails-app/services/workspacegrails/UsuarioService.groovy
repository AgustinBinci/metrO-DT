package workspacegrails

import grails.transaction.Transactional;
import workspacegrails.Servicio;

@Transactional
class UsuarioService extends Servicio {
	def coleccionesService;

	@Override
	public Boolean guardar(def unUsuario) {
		if (unUsuario.save(flush: true)) return true;
		return false;
	}

	@Override
	public Boolean eliminar(def unUsuario) {
		def equipos = unUsuario.getEquipos();
		coleccionesService.eliminarObjetosDeColeccion(equipos);
		unUsuario.delete(flush: true);
		if (unUsuario.hasErrors()) return false;
		return true;
	}

	@Override
	public def crear(def params) {
		try {
			//Instancio opciones del juego
			OpcionesDelJuego opcionesDelJuego = OpcionesDelJuego.getAll().get(0);

			//Creo el usuario
			Usuario unUsuario = new Usuario(nombre: params.nombre, apellido: params.apellido, contrasenia: encriptadorService.getSha256De(params.contrasenia),
										email: params.email, nombreDelEquipo: params.nombreDelEquipo, admin: false,
										presupuesto: opcionesDelJuego.getPresupuestoInicial(), cambiosGranDt: opcionesDelJuego.getCambiosGranDtIniciales(), 												cambiosInternos: opcionesDelJuego.getCambiosInternosIniciales(),
										cambiosDeSuplente: opcionesDelJuego.getCambiosDeSuplenteIniciales());

			//Formacion y equipo
			Formacion unaFormacion = Formacion.findByCantidadDeDefensoresAndCantidadDeVolantesAndCantidadDeDelanteros(4, 4, 2);
			EquipoDeUnaFecha unEquipo = new EquipoDeUnaFecha(fecha: FechaActual.first().getNumeroDeFecha(), formacion: unaFormacion);

			unUsuario.addToEquipos(unEquipo);

			return unUsuario;
		}
		catch(Exception unaExcepcion) {
			throw unaExcepcion;
		}
	}

}
