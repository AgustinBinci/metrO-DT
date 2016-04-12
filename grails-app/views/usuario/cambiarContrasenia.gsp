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
			    <g:form action="cambiarContrasenia" role="form">

					<div class="form-group row">
						<div class="col-md-4">

							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="password" name="contraseniaActual" maxlength="50" placeholder="Contraseña actual..." id="contraseniaActualInput" value="${contraseniaActual}" required autofocus>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>
				
						</div>
					</div>

					<div class="form-group row">
						<div class="col-md-4">

							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="password" name="nuevaContrasenia" maxlength="50" placeholder="Nueva contraseña..." id="contraseniaInput" value="${nuevaContrasenia}" required>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>

						</div>
					</div>

					<div class="form-group row">
						<div class="col-md-4">

							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="password" name="nuevaContraseniaRepetida" maxlength="50" placeholder="Confirmar nueva contraseña..." id="contraseniaRepetidaInput" value="${nuevaContraseniaRepetida}" required>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>

						</div>
					</div>

					<div class="form-group row">
						<div class="col-md-4">
							<input type="submit" id="guardarUsuario" class="btn btn-danger btn-lg col-md-12" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
						</div>
					</div>

			    </g:form>
		    </div>

        </div>
    </body>
</html>
