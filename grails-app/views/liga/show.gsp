<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.show.label" args="["${torneo?.getNombre()}"]" /></title>
    </head>
    <body>
        <div id="show-liga" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="["${torneo?.getNombre()}"]" /></h1>
			<br/><br/>
			<div class="container">
				<table class="table table-bordered table-hover">
					<tr>
						<th>Posicion</th>
						<th>Nombre</th>
						<th>Equipo</th>
						<th>Puntaje total</th>
						<g:each in="${fechas}" var="fecha">
							<th>Fecha ${fecha.toString()}</th>
						</g:each>
					</tr>
					<g:each in="${torneo?.getPuntajesGenerales()}" var="usuario" status="counter" >
						<tr>
							<td><p class="texto-filas">${counter + 1}</p></td>
							<td><p class="texto-filas">${usuario?.key?.getNombre()}</p></td>
							<td><p class="texto-filas">${usuario?.key?.getNombreDelEquipo()}</p></td>
							<td><p class="texto-filas">${usuario?.value?.toString()}</p></td>
						</tr>
					</g:each>
				</table>
			</div>
			<div id="check">
				<input type="checkbox" id="unCheck" onclick="funcion('unCheck', 'check')" /> hola
			</div>
			<script type="text/javascript">
				function funcion(elemento, id) {
					var unDiv = document.getElementById(id);
					var unCheck = document.getElementById(elemento);
					unDiv.innerHTML += "<br/><p>esta marcado!</p>";
				}
			</script>
        </div>
    </body>
</html>
