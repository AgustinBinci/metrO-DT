<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Participantes</title>
    </head>
    <body>
        <div id="index-usuario" class="content scaffold-index" role="main">
            <h1>Participantes</h1>
			<br/><br/>
		<div class="container">
			<table class="table table-bordered">
				<g:if test="${usuarios}">
					<tr class="active">
						<th>Nombre</th>
						<th>Equipo</th>
						<th>Admin</th>
						<th>Acciones</th>
					</tr>
				</g:if>
				<g:each in="${usuarios}" var="usuario">
					<g:if test="${usuario?.getAdmin()}"><tr class="success"></g:if>
					<g:else><tr class="danger"></g:else>
						<td><p class="texto-filas">${usuario?.getNombre()} ${usuario?.getApellido()}</p></td>
						<td>
							<g:link class="text-primary" action="showEquipo" params="[id: usuario?.getId()]"><p class="texto-filas">${usuario?.getNombreDelEquipo()} </p></g:link>
						</td>
						<td>
							<g:if test="${usuario?.getAdmin()}">
								<span class="glyphicon glyphicon-ok" style="color:white"></span>
							</g:if>
							<g:else>
								<button class="btn btn-danger btn-md"><span class="glyphicon glyphicon-remove" style="color:white"></span></button>
							</g:else>
						</td>
						<td>
							<g:if test="${session?.user?.getAdmin()}">
								<g:link class="btn btn-danger" action="delete" params="[id:usuario.getId()]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove" style="color:white"></span> <b><g:message code="default.delete" /></b></g:link>
								<g:if test="${usuario?.getId() == session?.user?.getId()}">
									<g:link class="btn btn-warning" action="edit" params="[id:usuario.getId()]"><span class="glyphicon glyphicon-edit" style="color:white"></span> <b><g:message code="default.edit" /></b></g:link>
								</g:if>
							</g:if>
							<g:elseif test="${usuario?.getId() == session?.user?.getId()}">
									<g:link class="btn btn-danger" action="delete" params="[id:usuario.getId()]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove" style="color:white"></span> <b><g:message code="default.delete" /></b></g:link>
									<g:link class="btn btn-warning" action="edit" params="[id:usuario.getId()]"><span class="glyphicon glyphicon-edit" style="color:white"></span> <b><g:message code="default.edit" /></b></g:link>
							</g:elseif>
						</td>
					<tr>
				</g:each>
			</table>
		</div>
        </div>
    </body>
</html>
