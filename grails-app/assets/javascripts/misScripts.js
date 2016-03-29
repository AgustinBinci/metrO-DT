window.onload = function() {

//********************************************************************************************************************************************************************************
//***********************************************************************************FUNCIONES************************************************************************************
//********************************************************************************************************************************************************************************

	//Funcion para activar casilla de error.
	//Pre: recibe por parametro los elementos a utilizar.
	function activarError(divInput, span, status) {
		divInput.removeClass("has-error");
		divInput.removeClass("has-success");
		divInput.addClass("has-error");

		span.removeClass("glyphicon-ok");
		span.removeClass("glyphicon-remove");
		span.addClass("glyphicon-remove");

		status.val("(error)");
	}

	//Funcion para activar casilla de exitoso.
	//Pre: idem activarError.
	function activarExito(divInput, span, status) {
		divInput.removeClass("has-error");
		divInput.removeClass("has-success");
		divInput.addClass("has-success");

		span.removeClass("glyphicon-ok");
		span.removeClass("glyphicon-remove");
		span.addClass("glyphicon-ok");

		status.val("(success)");
	}

	//Funcion para chequear el texto segun una expresion regular
	function chequearTextoSegunRegExp(texto, regExp) {
		if (regExp.test(texto)) return true;
		return false;
	}

	//Funcion para agregar el asterisco si el campo es requerido
	function agregarAsteriscoSiCorresponde(elemento) {
		if ( elemento.is('[required]') ) {
			var span = elemento.parent().prev().children();
			if (span.length) {
				span.removeClass("glyphicon glyphicon-asterisk asterisco");
				span.addClass("glyphicon glyphicon-asterisk asterisco");
			}
			else	{
				span = $('<span/>');
				span.addClass("glyphicon glyphicon-asterisk asterisco");
				elemento.parent().prev().append(" ");
				elemento.parent().prev().append(span);
			}
		}
	}

	//Funcion para validar un campo requerido
	function validarCampoRequerido(elemento) {
		var valor = elemento.val();

		if ( elemento.is('[required]') ) {		
			if ( valor != "" ) activarExito(elemento.parent(), elemento.next(), elemento.next().next());
			else activarError(elemento.parent(), elemento.next(), elemento.next().next());
		}
	}

	//Funcion para validar minimos y maximos de un campo
	function validarMaximosYminimos(elemento) {
		var minimo = elemento.attr("min");
		var maximo = elemento.attr("max");
		var valor = elemento.val();

		if (minimo != null) {
			valor = parseInt(valor);
			if (valor >= minimo) {
				activarExito(elemento.parent(), elemento.next(), elemento.next().next());
				if (maximo != null) {
					if (valor > maximo) activarError(elemento.parent(), elemento.next(), elemento.next().next());
				}
			}
			else activarError(elemento.parent(), elemento.next(), elemento.next().next());
		}

		if (maximo != null) {
			valor = parseInt(valor);
			if (valor <= maximo) {
				activarExito(elemento.parent(), elemento.next(), elemento.next().next());
				if (minimo != null) {
					if (valor < minimo) activarError(elemento.parent(), elemento.next(), elemento.next().next());
				}
			}
			else activarError(elemento.parent(), elemento.next(), elemento.next().next());
		}
	}

	//Funcion para validar la longitud de un campo
	function validarLongitud(elemento) {
		var longitud = elemento.attr("maxlength");
		var valor = elemento.val();

		if (longitud != null) {
			if (valor.length <= longitud) {
				activarExito(elemento.parent(), elemento.next(), elemento.next().next());
				if ( elemento.is('[required]') ) {
					if ( valor == "" ) activarError(elemento.parent(), elemento.next(), elemento.next().next());
				}
			}
			else activarError(elemento.parent(), elemento.next(), elemento.next().next());
		}
	}

	//Funcion para validar la expresion regular de un campo
	function validarExpresionRegular(elemento) {
		var regExp = elemento.attr("pattern");
		var valor = elemento.val();

		if (regExp != null) {
			var regExpCheck = new RegExp(regExp);
			if (valor.search(regExpCheck) >= 0) activarExito(elemento.parent(), elemento.next(), elemento.next().next());
			else activarError(elemento.parent(), elemento.next(), elemento.next().next());
		}
	}

//********************************************************************************************************************************************************************************
//**************************************************************************************CODIGO************************************************************************************
//********************************************************************************************************************************************************************************

	//Exito y error en formularios
	if ( $(".form-control").length ) {

		//Al iniciar
		$(".form-control").each(function() {
			//Variables
			var elemento = $(this);

			//Llamado a funciones
			agregarAsteriscoSiCorresponde(elemento);
			validarCampoRequerido(elemento);
			validarLongitud(elemento);
			validarMaximosYminimos(elemento);
			validarExpresionRegular(elemento);
		});

		//En algun cambio en el campo
		$(".form-control").on("paste input keyup", function() {
			//Variables
			var elemento = $(this);

			//Llamado a funciones
			validarCampoRequerido(elemento);
			validarLongitud(elemento);
			validarMaximosYminimos(elemento);
			validarExpresionRegular(elemento);
		});

	}
	
}
