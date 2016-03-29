<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${club.nombre}</title>
    </head>
    <body>
        <div id="show-club" class="content scaffold-show" role="main">
            <h1>${club.nombre}</h1>
			<br/><br/>

		<div class="container">
			<table class="table table-bordered table-hover">
				<tr class="active">
					<th>Posicion</th>
					<th>Nombre</th>
					<th>Precio</th>
					<th>Acciones</th>
				</tr>

				<g:each in="${jugadores}" var="jugador">
					<g:if test="${jugador?.getPosicion()?.toString() == 'Arquero'}">
						<tr class="success">
							<td><p class="texto-filas">${jugador.posicion}</p></td>
							<td><p class="texto-filas">${jugador.nombre}</p></td>
							<td><p class="texto-filas">$${jugador.precio}</p></td>
							<td>
								<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.id]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
								<g:if test="${session.user?.getAdmin()}">
									<g:link class="btn btn-warning" controller="jugador" action="edit" params="[id:jugador.id]"><span class="glyphicon glyphicon-edit"></span> <b>Editar</b></g:link>
									<g:link class="btn btn-danger" controller="jugador" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
								</g:if>
							</td>
						</tr>
					</g:if>
				</g:each>
				<g:each in="${jugadores}" var="jugador">
					<g:if test="${jugador?.getPosicion()?.toString() == 'Defensor'}">
						<tr class="warning">
							<td><p class="texto-filas">${jugador.posicion}</p></td>
							<td><p class="texto-filas">${jugador.nombre}</p></td>
							<td><p class="texto-filas">$${jugador.precio}</p></td>
							<td>
								<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.id]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
								<g:if test="${session.user?.getAdmin()}">
									<g:link class="btn btn-warning" controller="jugador" action="edit" params="[id:jugador.id]"><span class="glyphicon glyphicon-edit"></span> <b><g:message code="default.edit" /></b></g:link>
									<g:link class="btn btn-danger" controller="jugador" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
								</g:if>
							</td>
						</tr>
					</g:if>
				</g:each>
				<g:each in="${jugadores}" var="jugador">
					<g:if test="${jugador?.getPosicion()?.toString() == 'Volante'}">
						<tr  class="info">
							<td><p class="texto-filas">${jugador.posicion}</p></td>
							<td><p class="texto-filas">${jugador.nombre}</p></td>
							<td><p class="texto-filas">$${jugador.precio}</p></td>
							<td>
								<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.id]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
								<g:if test="${session.user?.getAdmin()}">
									<g:link class="btn btn-warning" controller="jugador" action="edit" params="[id:jugador.id]"><span class="glyphicon glyphicon-edit"></span> <b><g:message code="default.edit" /></b></g:link>
									<g:link class="btn btn-danger" controller="jugador" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
								</g:if>
							</td>
						</tr>
					</g:if>
				</g:each>
				<g:each in="${jugadores}" var="jugador">
					<g:if test="${jugador?.getPosicion()?.toString() == 'Delantero'}">
						<tr class="danger">
							<td><p class="texto-filas">${jugador.posicion}</p></td>
							<td><p class="texto-filas">${jugador.nombre}</p></td>
							<td><p class="texto-filas">$${jugador.precio}</p></td>
							<td>
								<g:link class="btn btn-info" controller="jugador" action="showStatistics" params="[id:jugador.id]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
								<g:if test="${session.user?.getAdmin()}">
									<g:link class="btn btn-warning" controller="jugador" action="edit" params="[id:jugador.id]"><span class="glyphicon glyphicon-edit"></span> <b><g:message code="default.edit" /></b></g:link>
									<g:link class="btn btn-danger" controller="jugador" action="delete" params="[id:jugador.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
								</g:if>
							</td>
						</tr>
					</g:if>
				</g:each>

				<tr>
					<td/>
					<td/>
					<td/>
					<td><g:link class="btn btn-success" controller="jugador" action="create" params="[idClub:club.id]"><span class="glyphicon glyphicon-plus"></span> <b>Crear un jugador para este club</b></g:link></td>
				</tr>
			</table>
		</div>
  
        </div>
    </body>
</html>
