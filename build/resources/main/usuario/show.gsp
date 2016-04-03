<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${usuario?.getNombre() + " " + usuario?.getApellido()}</title>
    </head>
    <body>
        <div id="show-opcionesDelJuego" class="content scaffold-show" role="main">
            <h1>${usuario?.getNombre() + " " + usuario?.getApellido()}</h1>
			<br/><br/>
			<div class="container">
				<div class="panel panel-default">
	
					<div class="panel-heading">
						<ul class="nav nav-pills">
							<li><g:link class="btn btn-danger-black" action="edit"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;<g:message code="default.edit" /></font></g:link></li>
							<li><g:link class="btn btn-danger-black" action="cambiarContrasenia"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;Cambiar contraseña</font></g:link></li>
							<li><g:link class="btn btn-danger-black" action="delete" id="${usuario?.getId()}"><span class="glyphicon glyphicon-remove"></span> <font style="font-weight: bold;">&nbsp;<g:message code="default.delete" /></font></g:link></li>
						</ul>
					</div>
					<table class="table table-hover">
						<tr>
							<td><p class="texto-filas-left">Nombre</p></td>
							<td><p class="texto-filas-right">${usuario?.getNombre()}</p></td>
						</tr>
						<tr>
							<td><p class="texto-filas-left">Apellido</p></td>
							<td><p class="texto-filas-right">${usuario?.getApellido()}</p></td>
						</tr>
						<tr>
							<td><p class="texto-filas-left">Email</p></td>
							<td><p class="texto-filas-right">${usuario?.getEmail()}</p></td>
						</tr>
						<tr>
							<td><p class="texto-filas-left">Nombre del equipo</p></td>
							<td><p class="texto-filas-right">${usuario?.getNombreDelEquipo()}</p></td>
						</tr>
					</table>
				</div>
			<div>
        </div>
    </body>
</html>
