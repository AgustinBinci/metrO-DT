<!DOCTYPE html>
<html>
    <head>
		<meta name="layout" content="main" />
        <title>${usuario?.getNombreDelEquipo()}</title>
    </head>
    <body>
        <div id="modify-equipoDeUnaFecha" class="content scaffold-modify" role="main">
			<h1>${usuario?.getNombreDelEquipo()}</h1>
			<br/><br/>
			<div class="container">
				<table class="table table-hover" align="center">
					<tr>
						<td><p class="texto-filas-left">DT</p></td>
						<td><p class="texto-filas-right">${usuario?.getNombre() + " " + usuario?.getApellido()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Presupuesto</p></td>
						<td><p class="texto-filas-right">$${usuario?.getPresupuesto()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cambios Gran DT</p></td>
						<td><p class="texto-filas-right">${usuario?.getCambiosGranDt()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cambios Internos</p></td>
						<td><p class="texto-filas-right">${usuario?.getCambiosInternos()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Formacion</p></td>
						<td><p class="texto-filas-right">${equipo?.getFormacion()?.toString()}</p></td>
					</tr>
				</table>
			</div>
			<div class="container-fluid row">
				<div id="jugadoresDelEquipo" class="col-md-6">
					<g:render template="jugadoresDelEquipo" model="[usuario: usuario, equipo: equipo]" />
				</div>

				<div class="col-md-6">

					<div>
						<g:form class="form-inline" action="modify">
							<div class="form-group">
								<label for="inputClub">Club</label>
								<g:select id="inputClub" class="form-control" from="${clubes}" value="${club?.getId()}" optionKey="id" name="idClub" />
							</div>
							<div class="form-group">
								<label for="inputPosicion">Posicion</label>
								<g:select id="inputPosicion" class="form-control" from="${posiciones}" value="${posicion?.getId()}" optionKey="id" name="idPosicion" noSelection="${['null':'Todas las posiciones']}" />
							</div>
						</g:form>
					</div>

					<div id="jugadores"></div>
				</div>

			</div>
        </div>
    </body>
</html>
