<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.show.label" args="['Fecha actual']" /></title>
    </head>
    <body>
        <div id="show-fechaActual" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="['Fecha actual']" /></h1>
			<br/><br/>
			<div class="container">
				<table class="table table-hover">
					<tr>
						<td><p class="texto-filas-left">Numero de fecha</p></td>
						<td><p class="texto-filas-right">${fechaActual?.getNumeroDeFecha().toString()}</p></td>
					<tr>
					<tr>
						<td><p class="texto-filas-left">Fecha de inicio</p></td>
						<td><p class="texto-filas-right">${fechaActual?.getFechaDeInicio()?.get(Calendar.DAY_OF_MONTH) + '/' + (fechaActual?.getFechaDeInicio()?.get(Calendar.MONTH) + 1) + '/' + fechaActual?.getFechaDeInicio()?.get(Calendar.YEAR)}</p></td>
					<tr>
					<tr>
						<td><p class="texto-filas-left">Hora de inicio</p></td>
						<td><p class="texto-filas-right">${fechaActual?.getFechaDeInicio()?.get(Calendar.HOUR_OF_DAY) + ":" + fechaActual?.getFechaDeInicio()?.get(Calendar.MINUTE)}</p></td>
					</tr>
				</table>
			</div>
        </div>
    </body>
</html>
