<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Olvidé mi contraseña</title>
    </head>
    <body>
        <div id="create-club" role="main">
            <h1>Olvidé mi contraseña</h1>
			<br/><br/>

		    <div class="container">
				<div class="col-md-4"></div>
				<div class="col-md-4">

				    <g:form action="regenerarContrasenia" role="form">

						<div class="form-group row">

							<div id="divInputEmail" class="has-error has-feedback">
					   			<input name="email" type="email" maxlength="50" pattern="^\w.*@\w.*([.])\w.*$" class="form-control input-lg" id="inputEmail" aria-describedby="inputEmailStatus" placeholder="E-mail..." value="${email}" required autofocus>
								<span id="spanEmail" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
								<span id="inputEmailStatus" class="sr-only">(error)</span>			
							</div>
				
						</div>


						<div class="form-group row">
							<input type="submit" id="guardarUsuario" class="btn btn-danger btn-lg col-md-12" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
						</div>

				    </g:form>
			    </div>
				<div class="col-md-4"></div>
			</div>

        </div>
    </body>
</html>
