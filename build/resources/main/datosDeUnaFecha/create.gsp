<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Crear puntajes y estadisticas de ${jugador?.toString()}</title>
    </head>
    <body>
		<div id="create-datosDeUnaFecha" class="content scaffold-create" role="main">
			<h1>Crear puntajes y estadisticas de ${jugador?.toString()}</h1>
			<br/><br/>
			<div class="container" align="center">
				<g:form action="create">
					<div class="form-group" align="center">
						<label for="fechaInput">Fecha</label>
						<input class="form-control" id="fechaInput" type="number" min="1" name="fecha" placeholder="Fecha" style="width: 30%" required>
					</div>
					<div class="form-group" align="center">
						<label for="puntajeDelDiarioInput">Puntaje del diario</label>
						<input class="form-control" id="puntajeDelDiarioInput" type="number" step="0.5" name="puntajeDelDiario" value="0" style="width: 30%" required>
					</div>
					<div class="form-group" align="center">
						<label for="golesAfavorInput">Goles a favor</label>
						<input class="form-control" id="golesAfavorInput" type="number" name="golesAfavor" value="0" style="width: 30%" required>
					</div>
					<div class="form-group" align="center">
						<label for="golesEnContraInput">Goles en contra</label>
						<input class="form-control" id="golesEnContraInput" type="number" name="golesEnContra" value="0" style="width: 30%" required>
					</div>
					<div class="form-group" align="center">
						<label for="golesDePenalInput">Goles de penal</label>
						<input class="form-control" id="golesDePenalInput" type="number" name="golesDePenal" value="0" style="width: 30%" required>
					</div>
					<div class="form-group" align="center">
						<label for="penalesErradosInput">Penales errados</label>
						<input class="form-control" id="penalesErradosInput" type="number" name="penalesErrados" value="0" style="width: 30%" required>
					</div>
					<g:if test="${jugador?.getPosicion().toString() == 'Arquero'}">
						<div class="form-group" align="center">
							<label for="penalesAtajadosInput">Penales atajados</label>
							<input class="form-control" id="penalesAtajadosInput" type="number" name="penalesAtajados" value="0" style="width: 30%" required>
						</div>
						<div class="form-group" align="center">
							<label for="golesRecibidosInput">Goles recibidos</label>
							<input class="form-control" id="golesRecibidosInput" type="number" name="golesRecibidos" value="0" style="width: 30%" required>
						</div>
					</g:if>
					<g:elseif test="${jugador?.getPosicion().toString() == 'Defensor'}">
						<div class="form-group" align="center">
							<label for="golesRecibidosInput">Goles recibidos</label>
							<input class="form-control" id="golesRecibidosInput" type="number" name="golesRecibidos" value="0" style="width: 30%" required>
						</div>
					</g:elseif>
					<div class="form-group" align="center">
						<div class="checkbox">
							<label>
								<input name="amonestado" type="checkbox">Fue amonestado
							</label>
						</div>
					</div>
					<div class="form-group" align="center">
						<div class="checkbox">
							<label>
								<input name="expulsado" type="checkbox">Fue expulsado
							</label>
						</div>
					</div>
					<div class="form-group" align="center">
						<div class="checkbox">
							<label>
								<input name="chenemigo" type="checkbox">Fue chenemigo
							</label>
						</div>
					</div>
					<div class="form-group" align="center">
						<div class="checkbox">
							<label>
								<input name="figura" type="checkbox">Fue figura
							</label>
						</div>
					</div>
					<div class="form-group" align="center">
						<div class="checkbox">
							<label>
								<input name="jugo" type="checkbox">Sumo
							</label>
						</div>
					</div>
					<input type="hidden" name="idJugador" value="${jugador?.getId()}">
						<input type="submit" class="btn btn-default" value="${message(code: 'default.create')}" align="center" style="font-weight: bold;">
				</g:form>
			</div>
		</div>
    </body>
</html>
