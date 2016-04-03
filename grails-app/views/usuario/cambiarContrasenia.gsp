<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Cambiar contraseña</title>
    </head>
    <body>
        <div id="create-club" role="main">
            <h1>Cambiar contraseña</h1>
			<br/><br/>

		    <div class="container">
			    <g:form action="cambiarContrasenia" class="formulario">

				<div class="form-group">
					<label class="control-label" for="contraseniaActualInput">Contraseña actual</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="password" name="contraseniaActual" maxlength="50" placeholder="Contraseña actual" id="contraseniaActualInput" value="${contraseniaActual}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="nuevaContraseniaInput">Nueva contrasenia</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="password" name="nuevaContrasenia" maxlength="50" placeholder="Nueva contraseña" id="nuevaContraseniaInput" value="${nuevaContrasenia}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
			    </g:form>
		    </div>

        </div>
    </body>
</html>
