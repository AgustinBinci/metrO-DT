<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${jugador?.getNombre()}</title>
    </head>
    <body>
        <div id="showStatistics-jugador" class="content scaffold-showStatistics" role="main">
            <h1>${jugador?.getNombre()}</h1>
			<br/><br/>
			<div class="container">
				<table class="table table-honver" align="center">
					<tr>
						<td><p class="texto-filas-left">Puntaje total</p></td>
						<td><p class="texto-filas-right">${jugador?.getPuntajeTotal()}</td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de partidos jugados</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDePartidosJugados()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de goles convertido</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeGolesAfavor() + jugador?.getCantidadDeGolesDePenales()} (${jugador?.getCantidadDeGolesDePenales()} de penal)</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de goles en contra</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeGolesEnContra()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de goles en contra</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeGolesEnContra()}</p></td>
					</tr>
					<g:if test="${jugador?.getPosicion().toString() == 'Arquero'}">
						<tr>
							<td><p class="texto-filas-left">Cantidad de penales atajados</p></td>
							<td><p class="texto-filas-right">${jugador?.getCantidadDePenalesAtajados()}</p></td>
						</tr>
						<tr>
							<td><p class="texto-filas-left">Cantidad de goles recibidos</p></td>
							<td><p class="texto-filas-right">${jugador?.getCantidadDeGolesRecibidos()}</p></td>
						</tr>
						<tr>
							<td><p class="texto-filas-left">Cantidad de vallas invictas</p></td>
							<td><p class="texto-filas-right">${jugador?.getCantidadDeVallasInvictas()}</p></td>
						</tr>
					</g:if>
					<g:elseif test="${jugador?.getPosicion().toString() == 'Defensor'}">
						<tr>
							<td><p class="texto-filas-left">Cantidad de vallas invictas</p></td>
							<td><p class="texto-filas-right">${jugador?.getCantidadDeVallasInvictas()}</p></td>
						</tr>
					</g:elseif>
					<tr>
						<td><p class="texto-filas-left">Cantidad de amonestaciones</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeAmonestaciones()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de expulsiones</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeExpulsiones()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de chenemigos</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeChenemigos()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Cantidad de figuras</p></td>
						<td><p class="texto-filas-right">${jugador?.getCantidadDeFiguras()}</p></td>
					</tr>
				</table>
				<g:if test="${session.user?.getAdmin()}">
					<div class="container" align="center">
						<g:link class="btn btn-success" controller="datosDeUnaFecha" action="create" params="[idJugador:jugador?.getId()]"><g:message code="default.newF.label" args="['fecha']" /></g:link>
					</div>
				</g:if>
			</div>
			<g:each in="${datosDelasFechas?}" var="datos">
				<div class="container">
					<table class="table table-honver table-bordered" align="center">
						<g:if test="${datos?.getJugo()}">
							<tr class="success"><td><h2 align="center"><font size="3"><b>Fecha ${datos?.getFecha().toString()}</b></font></h2></td></tr>
						</g:if>
						<g:else><tr class="danger"><td><h2 align="center"><font size="3"><b>Fecha ${datos?.getFecha().toString()}</b></font></h2></td></tr></g:else>
						<tr>
							<table class="table table-honver" align="center">
								<tr>
									<td><p class="texto-filas-left">Sumo</p></td>
									<g:if test="${datos?.getJugo()}"><td><p class="texto-filas-right">SI</p></td></g:if>
									<g:else><td><p class="texto-filas-right">NO</p></td></g:else>
								</tr>
								<g:if test="${datos?.getJugo()}">
									<tr>
										<td><p class="texto-filas-left">Puntaje del diario</p></td>
										<td><p class="texto-filas-right">${datos?.getPuntajeDelDiario()}</p></td>
									</tr>
								</g:if>
								<tr>
									<td><p class="texto-filas-left">Goles a favor</p></td>
									<td><p class="texto-filas-right">${datos?.getGolesAfavor()}</p></td>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Goles a favor</p></td>
									<td><p class="texto-filas-right">${datos?.getGolesAfavor()}</p></td>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Goles en contra</p></td>
									<td><p class="texto-filas-right">${datos?.getGolesEnContra()}</p></td>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Goles de penal</p></td>
									<td><p class="texto-filas-right">${datos?.getGolesDePenal()}</p></td>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Penales errados</p></td>
									<td><p class="texto-filas-right">${datos?.getPenalesErrados()}</p></td>
								</tr>
								<g:if test="${jugador?.getPosicion().toString() == 'Arquero'}">
									<tr>
										<td><p class="texto-filas-left">Penales atajados</p></td>
										<td><p class="texto-filas-right">${datos?.getPenalesAtajados()}</p></td>
									</tr>
									<tr>
										<td><p class="texto-filas-left">Goles recibidos</p></td>
										<g:if test="${datos?.getGolesRecibidos() == 0}">
											<td><p class="texto-filas-right">Valla invicta!</p></td>
										</g:if>
										<g:else>
											<td><p class="texto-filas-right">${datos?.getGolesRecibidos()}</p></td>
										</g:else>
									</tr>
								</g:if>
								<g:elseif test="${jugador?.getPosicion().toString() == 'Defensor'}">
									<tr>
										<td><p class="texto-filas-left">Goles recibidos</p></td>
										<g:if test="${datos?.getGolesRecibidos() == 0}">
											<td><p class="texto-filas-right">Valla invicta!</p></td>
										</g:if>
										<g:else>
											<td><p class="texto-filas-right">${datos?.getGolesRecibidos()}</p></td>
										</g:else>
									</tr>
								</g:elseif>
								<tr>
									<td><p class="texto-filas-left">Amonestado</p></td>
									<g:if test="${datos?.getAmonestado()}"><td><p class="texto-filas-right">SI</p></td></g:if>
									<g:else><td><p class="texto-filas-right">NO</p></td></g:else>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Expulsado</p></td>
									<g:if test="${datos?.getExpulsado()}"><td><p class="texto-filas-right">SI</p></td></g:if>
									<g:else><td><p class="texto-filas-right">NO</p></td></g:else>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Chenemigo</p></td>
									<g:if test="${datos?.getChenemigo()}"><td><p class="texto-filas-right">SI</p></td></g:if>
									<g:else><td><p class="texto-filas-right">NO</p></td></g:else>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Figura</p></td>
									<g:if test="${datos?.getFigura()}"><td><p class="texto-filas-right">SI</p></td></g:if>
									<g:else><td><p class="texto-filas-right">NO</p></td></g:else>
								</tr>
								<tr>
									<td><p class="texto-filas-left">Puntaje total</p></td>
									<td><p class="texto-filas-right">${jugador?.getPuntajeTotalDeLaFecha(datos?.getFecha())}</p></td>
								</tr>
							</table>
						</tr>
					</table>
					<g:if test="${session.user?.getAdmin()}">
						<div class="container" align="center">
							<g:link class="btn btn-success" controller="datosDeUnaFecha" action="edit" params="[id:datos?.getId()]"><g:message code="default.edit.label" args="["fecha ${datos?.getFecha().toString()}"]" /></g:link>
						</div>
					</g:if>
				</div>
			</g:each>
        </div>
    </body>
</html>
