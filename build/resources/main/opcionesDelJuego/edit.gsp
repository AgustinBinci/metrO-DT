<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.edit.label" args="['opciones generales']" /></title>
    </head>
    <body>
        <div id="edit-opcionesDelJuego" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="['opciones generales']" /></h1>
			<br/><br/>
		<div class="container">
		    <g:form action="edit" class="formulario">
				<div class="form-group">
					<label class="control-label" for="presupuestoInicialInput">Presupuesto inicial</label>
					<div id="divPresupuestoInicialInput" class="has-success has-feedback">
						<input class="form-control" id="presupuestoInicialInput" type="number" min="0" max="2147483645" name="presupuestoInicial" value="${opcionesDelJuego?.getPresupuestoInicial()}"  required>
						<span id="spanPresupuestoInicial" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
						<span id="presupuestoInicialInputStatus" class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label" for="cambiosInternosInicialesInput">Cambios internos iniciales</label>
					<div id="divCambiosInternosInicialesInput" class="has-success has-feedback">
						<input class="form-control" id="cambiosInternosInicialesInput" type="number" min="0" max="2147483645" name="cambiosInternosIniciales" value="${opcionesDelJuego?.getCambiosInternosIniciales()}" required>
						<span id="spanCambiosInternosInicialesInput" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
						<span id="cambiosInternosInicialesInputStatus" class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label" for="cambiosGranDtInicialesInput">Cambios Gran DT iniciales</label>
					<div id="divCambiosGranDtInicialesInput" class="has-success has-feedback">
						<input class="form-control" id="cambiosGranDtInicialesInput" type="number" min="0" max="2147483645" name="cambiosGranDtIniciales" value="${opcionesDelJuego?.getCambiosGranDtIniciales()}" required>
						<span id="spanCambiosGranDtInicialesInput" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
						<span id="cambiosGranDtInicialesInputStatus" class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label" for="cambiosDeSuplenteInicialesInput">Cambios de suplentes iniciales</label>
					<div id="divCambiosDeSuplenteInicialesInput" class="has-success has-feedback">
						<input class="form-control" id="cambiosDeSuplenteInicialesInput" type="number" min="0" max="2147483645" name="cambiosDeSuplenteIniciales" value="${opcionesDelJuego?.getCambiosDeSuplenteIniciales()}" required>
						<span id="spanCambiosDeSuplenteInicialesInput" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
						<span id="cambiosDeSuplenteInicialesInputStatus" class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label" for="jugadoresDeUnMismoClubPermitidosInput">Cantidad de jugadores de un mismo club permitidos en un equipo</label>
					<div id="divJugadoresDeUnMismoClubPermitidosInput" class="has-success has-feedback">
						<input class="form-control" id="jugadoresDeUnMismoClubPermitidosInput" type="number" min="1" max="15" name="jugadoresDeUnMismoClubPermitidos" value="${opcionesDelJuego?.getJugadoresDeUnMismoClubPermitidos()}" required>
						<span id="spanJugadoresDeUnMismoClubPermitidosInput" class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
						<span id="jugadoresDeUnMismoClubPermitidosInputStatus" class="sr-only">(success)</span>
					</div>
				</div>
				<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}"style="font-weight: bold;">
		    </g:form>
		</div>
        </div>
    </body>
</html>
