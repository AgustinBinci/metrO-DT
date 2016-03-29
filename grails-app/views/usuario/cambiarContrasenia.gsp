<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Cambiar contraseña</title>
    </head>
    <body>
        <div id="create-club" class="container" role="main">
            <h1>Cambiar contraseña</h1>
			<br/><br/>
		    <div class="container" align="center">
			    <g:form action="cambiarContrasenia">
				<div class="form-group" align="center">
					<label for="contraseniaActualInput">Contraseña actual</label>
					<input class="form-control" type="password" name="contraseniaActual" id="contraseniaActualInput" value="${contraseniaActual}" style="width: 30%" required>
				</div>
				<div class="form-group" align="center">
					<label for="nuevaContraseniaInput">Nueva contraseña</label>
					<input class="form-control" type="password" name="nuevaContrasenia" id="nuevaContraseniaInput" value="${nuevaContrasenia}" style="width: 30%" required>
				</div> 
				<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
			    </g:form>
		    </div>

        </div>
    </body>
</html>
