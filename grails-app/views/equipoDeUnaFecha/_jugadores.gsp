<table id="tablaJugadores" class="table table-hover table-bordered table-condensed table-responsive">
		<g:each in="${jugadores}" var="jugador">
			<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero'}">
				<tr class="success">
					<td class="col-md-1"><p class="texto-filas">${jugador.posicion}</p></td>
					<td class="col-md-1"><p class="texto-filas">${jugador.nombre}</p></td>
					<td class="col-md-1"><p class="texto-filas">$${jugador.precio}</p></td>
					<td class="col-md-3">
						<button class="btn btn-success btn-sm" onclick="incorporarJugador(${jugador?.getId()}, true)"><span class="glyphicon glyphicon-plus"></span> <b>Titular</b></button>
						<button class="btn btn-danger btn-sm" onclick="incorporarJugador(${jugador?.getId()}, false)"><span class="glyphicon glyphicon-plus"></span> <b>Suplente</b></button>
					</td>
				</tr>
			</g:if>
		</g:each>
		<g:each in="${jugadores}" var="jugador">
			<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor'}">
				<tr class="warning">
					<td class="col-md-1"><p class="texto-filas">${jugador.posicion}</p></td>
					<td class="col-md-1"><p class="texto-filas">${jugador.nombre}</p></td>
					<td class="col-md-1"><p class="texto-filas">$${jugador.precio}</p></td>
					<td class="col-md-3">
						<button class="btn btn-success btn-sm" onclick="incorporarJugador(${jugador?.getId()}, true)"><span class="glyphicon glyphicon-plus"></span> <b>Titular</b></button>
						<button class="btn btn-danger btn-sm" onclick="incorporarJugador(${jugador?.getId()}, false)"><span class="glyphicon glyphicon-plus"></span> <b>Suplente</b></button>
					</td>
				</tr>
			</g:if>
		</g:each>
		<g:each in="${jugadores}" var="jugador">
			<g:if test="${jugador?.getPosicion()?.toString() == 'Volante'}">
				<tr  class="info">
					<td class="col-md-1"><p class="texto-filas">${jugador.posicion}</p></td>
					<td class="col-md-1"><p class="texto-filas">${jugador.nombre}</p></td>
					<td class="col-md-1"><p class="texto-filas">$${jugador.precio}</p></td>
					<td class="col-md-3">
						<button class="btn btn-success btn-sm" onclick="incorporarJugador(${jugador?.getId()}, true)"><span class="glyphicon glyphicon-plus"></span> <b>Titular</b></button>
						<button class="btn btn-danger btn-sm" onclick="incorporarJugador(${jugador?.getId()}, false)"><span class="glyphicon glyphicon-plus"></span> <b>Suplente</b></button>
					</td>
				</tr>
			</g:if>
		</g:each>
		<g:each in="${jugadores}" var="jugador">
			<g:if test="${jugador?.getPosicion()?.toString() == 'Delantero'}">
				<tr class="danger">
					<td class="col-md-1"><p class="texto-filas">${jugador.posicion}</p></td>
					<td class="col-md-1"><p class="texto-filas">${jugador.nombre}</p></td>
					<td class="col-md-1"><p class="texto-filas">$${jugador.precio}</p></td>
					<td class="col-md-3">
						<button class="btn btn-success btn-sm" onclick="incorporarJugador(${jugador?.getId()}, true)"><span class="glyphicon glyphicon-plus"></span> <b>Titular</b></button>
						<button class="btn btn-danger btn-sm" onclick="incorporarJugador(${jugador?.getId()}, false)"><span class="glyphicon glyphicon-plus"></span> <b>Suplente</b></button>
					</td>
				</tr>
			</g:if>
		</g:each>
	</table>
