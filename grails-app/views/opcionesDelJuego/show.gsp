<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Opciones generales</title>
    </head>
    <body>
        <div id="show-opcionesDelJuego" class="content scaffold-show" role="main">
            <h1>Opciones generales</h1>
			<br/><br/>
			<div class="container">
				<div class="panel panel-default">
	
					<g:if test="${session?.user?.getAdmin()}">
						<div class="panel-heading">
							<ul class="nav nav-pills">
								<li><g:link class="btn btn-danger-black" action="edit"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;&nbsp;<g:message code="default.edit" /></font></g:link></li>
								<li><g:link class="btn btn-danger-black" action="edit"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;&nbsp;<g:message code="default.edit" /> premio por fecha ganada</font></g:link></li>
							</ul>
						</div>
					</g:if>

					<table class="table table-hover">
						<tbody>
							<tr>
								<td><p class="texto-filas-left">Presupuesto inicial</p></td>
								<td><p class="texto-filas-right">$${opcionesDelJuego?.getPresupuestoInicial()}</p></td>
							</tr>
							<tr>
								<td><p class="texto-filas-left">Cambios internos iniciales</p></td>
								<td><p class="texto-filas-right">${opcionesDelJuego?.getCambiosInternosIniciales()}</p></td>
							</tr>
							<tr>
								<td><p class="texto-filas-left">Cambios Gran DT iniciales</p></td>
								<td><p class="texto-filas-right">${opcionesDelJuego?.getCambiosGranDtIniciales()}</p></td>
							</tr>
							<tr>
								<td><p class="texto-filas-left">Cambios de suplente iniciales</p></td>
								<td><p class="texto-filas-right">${opcionesDelJuego?.getCambiosDeSuplenteIniciales()}</p></td>
							</tr>
							<tr>
								<td><p class="texto-filas-left">Cantidad maxima de jugadores de un mismo club en un equipo</p></td>
								<td><p class="texto-filas-right">${opcionesDelJuego?.getJugadoresDeUnMismoClubPermitidos()}</p></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="panel panel-default">
					<g:if test="${session?.user?.getAdmin()}">
						<div class="panel-heading">
							<h2><p class="texto-filas-ind" style="color: white;">${torneoPrincipal?.getPremioPorFechaGanada()?.toString()}</p></h2>
						</div>
					</g:if>
							
					<table class="table table-bordered table-hover">
						<tbody>
							<tr>
								<table class="table table-hover">
									<tr>
										<td><p class="texto-filas-left">Presupuesto adicional</p></td>
										<td><p class="texto-filas-right">$${torneoPrincipal?.getPremioPorFechaGanada()?.getPresupuestoAdicional().toString()}</p></td>
									</tr>

									<g:each in="${torneoPrincipal?.getPremioPorFechaGanada()?.getCambiosAeleccion()}" var="cambio">
										<tr>
											<td><p class="texto-filas-left">Cambio a elegir entre</p></td>
											<td>
												<ul style="margin-right: 0%; margin-left: 70%">
													<g:if test="${cambio?.getCambioInterno()}">
														<li><p>Cambio interno</p></li>
													</g:if>
													<g:if test="${cambio?.getCambioDeSuplentes()}">
														<li><p>Cambio de suplentes</p></li>
													</g:if>
													<g:if test="${cambio?.getCambioGranDt()}">
														<li><p>Cambio Gran DT</p></li>
													</g:if>
												</ul>
											</td>
										</tr>
									</g:each>
								</table>
							</tr>
						</tbody>
					</table>
				</div>

			<div>
        </div>
    </body>
</html>
