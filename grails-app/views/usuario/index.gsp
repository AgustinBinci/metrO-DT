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
				<div class="panel panel-default">

					<table class="table table-hover">
						<g:if test="${usuarios}">
							<tr class="tr-grande">
								<th><p class="tr-grande">Nombre</p></th>
								<th><p class="tr-grande">Equipo</p></th>
								<th><p class="tr-grande">Admin</p></th>
								<th><p class="tr-grande">Acciones</p></th>
							</tr>
						</g:if>
						<g:each in="${usuarios}" var="usuario">
							<tr>
								<td><p class="texto-filas-left">${usuario?.getNombre()} ${usuario?.getApellido()}</p></td>
								<td>
									<g:link class="text-primary" controller="equipoDeUnaFecha" action="show" params="[idUsuario: usuario?.getId()]"><p class="texto-filas-left">${usuario?.getNombreDelEquipo()} </p></g:link>
								</td>
								<td>
									<g:if test="${usuario?.getAdmin()}">
										<button class="btn btn-transparent btn-md"><span class="glyphicon glyphicon-ok"></span></button>
									</g:if>
									<g:else>
										<button class="btn btn-transparent btn-md"><span class="glyphicon glyphicon-remove"></span></button>
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
        </div>
    </body>
</html>
