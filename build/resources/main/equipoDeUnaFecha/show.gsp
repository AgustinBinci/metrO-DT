<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${usuario?.getNombreDelEquipo()}</title>
    </head>
    <body>
        <div id="show-equipoDeUnaFecha" class="container-fluid" role="main">
			<h1>${usuario?.getNombreDelEquipo()}</h1>
			<br/><br/>
			<div class="container">
				<div class="panel panel-default">
					<div class="panel-heading">
						<ul class="nav nav-pills">
							<g:if test="${ (usuario?.getId() == session.user?.getId()) && torneo && (torneo.getTorneoPrincipal()) && !torneo.comenzoElTorneo() }">
								<li><g:link class="btn btn-danger-black" action="modify"><span class="glyphicon glyphicon-transfer"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Realizar modificaciones</font><span class="badge">99</span></g:link></li>
								<li><g:link class="btn btn-danger-black disabled" action="modify"><span class="glyphicon glyphicon-transfer"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio Gran DT&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosGranDt()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black disabled" action="modify"><span class="glyphicon glyphicon-sort"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio Interno&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosInternos()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black disabled" action="modify"><span class="glyphicon glyphicon-resize-full"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio de suplente&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosDeSuplente()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black disabled" action="modify"><span class="glyphicon glyphicon-question-sign"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio a elección&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosAeleccion().size()}</span></g:link></li>
							</g:if>
							<g:else>
								<li disabled><g:link class="btn btn-danger-black" action="modify"><span class="glyphicon glyphicon-transfer"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Realizar modificaciones</font><span class="badge">99</span></g:link></li>
								<li><g:link class="btn btn-danger-black" action="modify"><span class="glyphicon glyphicon-transfer"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio Gran DT&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosGranDt()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black" action="modify"><span class="glyphicon glyphicon-sort"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio Interno&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosInternos()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black" action="modify"><span class="glyphicon glyphicon-resize-full"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio de suplente&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosDeSuplente()}</span></g:link></li>
								<li><g:link class="btn btn-danger-black disabled" action="modify"><span class="glyphicon glyphicon-question-sign"></span> <font style="font-weight: bold;">&nbsp;&nbsp;Cambio a elección&nbsp;&nbsp;</font><span class="badge">${usuario?.getCambiosAeleccion().size()}</span></g:link></li>
							</g:else>
						</ul>
					</div>

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
							<td><p class="texto-filas-left">Formacion</p></td>
							<td><p class="texto-filas-right">${equipo?.getFormacion()?.toString()}</p></td>
						</tr>
					</table>				

					<table class="table table-bordered table-hover">
						<tr><td class="success"><h2 align="center"><b><font size=3>Titulares</td></tr>
						<tr><td>
							<table class="table table-bordered table-hover">
								<tr class="active">
									<th>Posicion</th>
									<th>Nombre</th>
									<th>Precio</th>
									<th>Acciones</th>
								</tr>

								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero' && jugador?.getTitular()}">
										<tr class="success">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor' && jugador?.getTitular()}">
										<tr class="warning">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Volante' && jugador?.getTitular()}">
										<tr class="info">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Delantero' && jugador?.getTitular()}">
										<tr class="danger">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
							</table>
						</td></tr>
					</table>




					<table class="table table-bordered table-hover">
						<tr><td class="danger"><h2 align="center"><b><font size=3>Suplentes</td></tr>
						<tr><td>
							<table class="table table-bordered table-hover">
								<tr class="active">
									<th>Posicion</th>
									<th>Nombre</th>
									<th>Precio</th>
									<th>Acciones</th>
								</tr>

								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero' && !jugador?.getTitular()}">
										<tr class="success">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor' && !jugador?.getTitular()}">
										<tr class="warning">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Volante' && !jugador?.getTitular()}">
										<tr class="info">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Delantero' && !jugador?.getTitular()}">
										<tr class="danger">
											<td><p class="texto-filas">${jugador?.getPosicion()}</p></td>
											<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
											<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
											</td>
										</tr>
									</g:if>
								</g:each>
							</table>
						</td></tr>
					</table>
				</div>
			</div>
        </div>
    </body>
</html>
