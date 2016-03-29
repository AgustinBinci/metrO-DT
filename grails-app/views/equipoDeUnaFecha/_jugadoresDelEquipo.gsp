<g:if test="${flash.message}">
	<div class="alert alert-info alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>Info!</strong> ${flash.message}
	</div>
</g:if>
<g:elseif test="${flash.errorMessage}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>Error!</strong> ${flash.errorMessage}
	</div>
</g:elseif>	

<table class="table table-bordered table-hover">
	<tr><td class="success"><h2 align="center"><b><font size=3>Titulares</td></tr>
	<tr><td>
		<table class="table table-bordered table-hover">
			<tr>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
			<tr>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
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
							<g:if test="${usuario?.getId() == session.user?.getId()}">
								<button class="btn btn-danger" onclick="eliminarJugador(${jugador?.getId()})"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></button>
							</g:if>
						</td>
					</tr>
				</g:if>
			</g:each>
		</table>
	</td></tr>
</table>
