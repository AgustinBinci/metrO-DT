<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${usuario?.getNombreDelEquipo()}</title>
    </head>
    <body>
        <div id="showEquipo-usuario" class="content scaffold-showEquipo" role="main">
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
				<g:if test="${usuario?.getId() == session.user?.getId()}">
					<div class="container" align="center">
						<g:link class="btn btn-success" action="showEquipo" params="[id:equipo?.getId(), cambios: true]"><span class="glyphicon glyphicon-plus"></span> <b>Realizar modificaciones</b></g:link>
					</div>
				</g:if>
			</div>
			<g:if test="${cambios}">
				<div class="container-fluid row">
					<div class="col-md-6">
			</g:if>
			<g:else> <div class="container"> </g:else>
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
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor' && jugador?.getTitular()}">
										<tr class="warning">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Volante' && jugador?.getTitular()}">
										<tr class="info">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="Delantero">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero' && jugador?.getTitular()}">
										<tr class="danger">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>

								<g:if test="${usuario?.getId() == session.user?.getId()}">
									<tr>
										<td/>
										<td/>
										<td/>
										<td><g:link class="btn btn-success" controller="jugador" action="create" params="[idClub:club?.id]"><span class="glyphicon glyphicon-plus"></span> <b>Agregar jugador</b></g:link></td>
									</tr>
								</g:if>
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
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor' && !jugador?.getTitular()}">
										<tr class="warning">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="jugador">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Volante' && !jugador?.getTitular()}">
										<tr class="info">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:each in="${equipo?.getJugadoresSeleccionados()}" var="Delantero">
									<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero' && !jugador?.getTitular()}">
										<tr class="danger">
											<td><p class="texto-filas">${jugador.posicion}</p></td>
											<td><p class="texto-filas">${jugador.nombre}</p></td>
											<td><p class="texto-filas">$${jugador.precio}</p></td>
											<td>
												<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.getIdJugador()]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
												<g:if test="${usuario?.getId() == session.user?.getId()}">
													<g:link class="btn btn-danger" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
												</g:if>
											</td>
										</tr>
									</g:if>
								</g:each>
								<g:if test="${usuario?.getId() == session.user?.getId()}">
									<tr>
										<td/>
										<td/>
										<td/>
										<td><g:link class="btn btn-success" controller="jugador" action="create" params="[idClub:club?.id]"><span class="glyphicon glyphicon-plus"></span> <b>Agregar jugador</b></g:link></td>
									</tr>
								</g:if>
							</table>
						</td></tr>
					</table>
				<g:if test="${cambios}">
					</div>
					<div class="col-md-6">
						<table class="table table-bordered table-hover">
							<tr><th>Hola</th></tr>
						</table>
					</div>
				</g:if>
			</div>
        </div>
    </body>
</html>
