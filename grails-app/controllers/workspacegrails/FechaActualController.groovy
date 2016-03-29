package workspacegrails

import grails.transaction.Transactional

@Transactional
class FechaActualController {

	def index() {
		return redirect (uri: "/");
	}
	
	def show() {
		if (session.user) {
			FechaActual fechaActual = FechaActual.first();
			if (fechaActual) return render(view: "show", model: [fechaActual: fechaActual]);
			else {
				flash.errorMessage = "No hay fecha";
				return render(view: "index");
			}
		}
		else return render(view: "index");
	}

	def edit() {
		if (session.user && session.user.getAdmin()) {
			FechaActual fechaActual = FechaActual.first();
			
			if (params.numeroDeFecha && params.fecha && params.hora) {
				Calendar unaFechaDeInicio = fechaActual.getFechaDeInicio();

				//Formateo fecha
				String anio = params.fecha.substring(0, 4);
				String mes = params.fecha.substring(5, 7);
				String dia = params.fecha.substring(8);

				//Formateo hora
				String hora = params.hora.substring(0, 2);
				String minutos = params.hora.substring(3);
				flash.message = hora + ":" + minutos;

				//Seteo valores
				fechaActual.setNumeroDeFecha(Integer.parseInt(params.numeroDeFecha));
				unaFechaDeInicio.set(Calendar.YEAR, Integer.parseInt(anio));
				unaFechaDeInicio.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
				unaFechaDeInicio.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
				unaFechaDeInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
				unaFechaDeInicio.set(Calendar.MINUTE, Integer.parseInt(minutos));
				unaFechaDeInicio.set(Calendar.SECOND, 0);
		
				//Guardo
				fechaActual.save()

				//Redirecciono
				return redirect(action: "show");
			}
			else {
				//Fecha
				Integer anio = fechaActual.getFechaDeInicio().get(Calendar.YEAR);
				Integer mes = fechaActual.getFechaDeInicio().get(Calendar.MONTH) + 1;
				Integer dia = fechaActual.getFechaDeInicio().get(Calendar.DAY_OF_MONTH);

				String fechaFormateada = anio.toString() + "-";
				if ((mes - 10) < 0) fechaFormateada += "0";
				fechaFormateada += mes.toString() + "-" + dia.toString();

				//Hora
				Integer hora = fechaActual.getFechaDeInicio().get(Calendar.HOUR_OF_DAY);
				Integer minutos = fechaActual.getFechaDeInicio().get(Calendar.MINUTE);

				String horaFormateada = hora.toString() + ":" + minutos.toString();
	
				return render(view: "edit", model: [fechaActual: fechaActual, fechaFormateada:fechaFormateada, horaFormateada: horaFormateada]);
			}
		}
		else {
			flash.errorMessage = "No tenes permisos para editar la fecha!";
			return render(view: "index");
		}
	}
  
}
