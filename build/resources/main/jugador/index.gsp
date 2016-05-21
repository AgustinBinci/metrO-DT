<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Jugadores</title>
    </head>
    <body>
        <div id="index-jugador" class="content scaffold-index" role="main">
            <h1>Jugadores</h1>
			<br/><br/>

		<div class="container">
		<div class="row">
			<p class="col-md-6">Posicion</p>
			<p class="col-md-6">Club</p>
		</div>
		<div class="row">
			<g:select class="col-md-6" from="workspaceGrails.Posicion?.list()" optionKey="id" noSelecion="['null': 'Todas']"/>
			<g:select class="col-md-6" from="workspaceGrails.Club?.list()" optionKey="id" noSelecion="['null': 'Todos']"/>
		</div>
			<table id="unaTabla" class="table table-bordered table-hover" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th>Posicion</th>
						<g:sortableColumn class="unaTabla" property="nombre" title="Nombre" titleKey="jugador?.getNombre()" />
						<g:sortableColumn class="unaTabla" property="club" title="Club" titleKey="jugador?.getClub().getNombre()" />
						<g:sortableColumn class="unaTabla" property="precio" title="Precio" titleKey="jugador?.getPrecio().toString()" />
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${jugadores}" var="jugador">
						<tr>
							<td><p class="texto-filas">${jugador?.getPosicion().toString()}</p></td>
							<td><p class="texto-filas">${jugador?.getNombre()}</p></td>
							<td><g:link controller="club" action="show" params="[id: jugador?.getClub()?.getId()]"><p class="texto-filas">${jugador?.getClub().getNombre()}</p></g:link></td>
							<td><p class="texto-filas">$${jugador?.getPrecio()}</p></td>
							<td>
								<g:link class="btn btn-info" action="showStatistics" params="[id:jugador?.id]"><span class="glyphicon glyphicon-stats"></span> <b>Puntajes</b></g:link>
								<g:if test="${session.user?.getAdmin()}">
									<g:link class="btn btn-warning" controller="jugador" action="edit" params="[id:jugador?.id]"><span class="glyphicon glyphicon-edit"></span> <b><g:message code="default.edit" /></b></g:link>
									<g:link class="btn btn-danger" controller="jugador" action="delete" params="[id:jugador?.id]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove"></span> <b><g:message code="default.delete" /></b></g:link>
								</g:if>
							</td>
						</tr>
					</g:each>

				    <g:if test="${session.user?.getAdmin()}">
					    <tr>
							<td/>
							<td/>
							<td/>
							<td/>
							<td><g:link class="btn btn-success" action="create"><span class="glyphicon glyphicon-plus"></span> <b><g:message code="default.new.label" args="['jugador']" /></b></g:link></td>
					    </tr>
				    </g:if>
				</tbody>
			</table>
		</div>

        </div>
    </body>
</html>
