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

						<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								</button>
							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
								<ul class="nav navbar-nav" role="navigation">
									<li><g:link action="edit"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;<g:message code="default.edit" /></font></g:link></li>
									<li><g:link action="cambiarContrasenia"><span class="glyphicon glyphicon-edit"></span> <font style="font-weight: bold;">&nbsp;Cambiar contraseña</font></g:link></li>
									<li><g:link action="deleteLogico" id="${usuario?.getId()}"><span class="glyphicon glyphicon-remove"></span> <font style="font-weight: bold;">&nbsp;<g:message code="default.delete" /></font></g:link></li>
								</ul>
							</div>

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
