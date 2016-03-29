<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Clubes</title>
    </head>
    <body>
	<div id="index-club" class="content scaffold-index" role="main">

		<h1>Clubes</h1>
		<br/><br/>
		</div class="container-fluid">

		    	<div class="container">
				<table id="clubTabla" class="table table-bordered table-hover">
					<tr class="active">
						<th>Nombre</th>
						<th>Escudo</th>
						<g:if test="${session.user?.getAdmin()}">
							<th>Acciones</th>
						</g:if>
					</tr>
					    <g:each in="${clubes}" var="club">
						<tr>
							<td><g:link action="show" params="[id:club.id]"> <p class="texto-filas">${club.nombre} </p></g:link></td>
							<td/>
							<g:if test="${session.user?.getAdmin()}">
								<td>
									<g:link class="btn btn-danger" action="delete" params="[id:club.getId()]" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><span class="glyphicon glyphicon-remove" style="color:white"></span> <b><g:message code="default.delete" /></b></g:link>
									<g:link class="btn btn-warning" action="edit" params="[id:club.getId()]"><span class="glyphicon glyphicon-edit" style="color:white"></span> <b><g:message code="default.edit" /></b></g:link>
								</td>
							</g:if>
						</tr>
					    </g:each>
					    <g:if test="${session.user?.getAdmin()}">
						    <tr>
							<td/>
							<td/>
							<td><g:link class="btn btn-success" action="create"><span class="glyphicon glyphicon-plus"></span> <b><g:message code="default.new.label" args="['club']" /></b></g:link>
						    </tr>
					    </g:if>
	
				</table>
			</div>
	
		</div>
		</div>

    </body>
</html>
