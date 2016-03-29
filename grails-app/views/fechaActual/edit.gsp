<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.edit.label" args="['fecha actual']" /></title>
    </head>
    <body>

        <div id="edit-fechaActual" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="['fecha actual']" /></h1>
			<br/><br/>
		    <g:form action="edit">
				<div class="form-group" align="center">
					<label for="numeroInput">Numero de fecha</label>
					<input class="form-control" id="numeroInput" type="number" name="numeroDeFecha" value="${fechaActual?.getNumeroDeFecha()}" style="width: 30%" required>
				</div>
				<div class="form-group" align="center">
					<label for="fechaInput">Fecha de inicio</label>
						<input class="form-control" id="fechaInput" type="date" name="fecha" value="${fechaFormateada}" style="width: 30%" required>
					</div>
				</div>
				<div class="form-group" align="center">
					<label for="horaInput">Hora de inicio</label>
						<input class="form-control" id="horaInput" type="time" name="hora" value="${horaFormateada}" style="width: 30%" required>
					</div>
				</div>

				<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
		    </g:form>
        </div>
    </body>
</html>
