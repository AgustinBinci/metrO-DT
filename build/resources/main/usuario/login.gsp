<!DOCTYPE html>
<html>
    <head>
		<meta name="layout" content="main" />
		<title>Login</title>
    </head>
    <body>
        <div id="create-usuario" class="content scaffold-create" role="main">
            <h1>Login</h1>
			<br/><br/>

		<div class="container">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-1"></div>

					<div class="col-md-10">
					    <g:form action="login" role="form">
						    	<div class="form-group row">
						    		
								<div id="divInputEmail" class="has-error has-feedback">
						   			<input name="email" type="email" maxlength="50" pattern="^\w.*@\w.*([.])\w.*$" class="form-control input-lg" id="inputEmail" aria-describedby="inputEmailStatus" placeholder="E-mail..." value="${email}" required autofocus>
									<span id="spanEmail" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
									<span id="inputEmailStatus" class="sr-only">(error)</span>			
								</div>

					  	    	</div>

						    	<div class="form-group row">
						    			
								<div id="divInputContrasenia" class="has-error has-feedback">
					   				<input name="contrasenia" type="password" maxlength="50" class="form-control input-lg" id="inputContrasenia" aria-describedby="inputContraseniaStatus" placeholder="Contraseña..." required>
									<span id="spanContrasenia" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
									<span id="inputContraseniaStatus" class="sr-only">(error)</span>
								</div>

					  	    	</div>

							<div class="form-group row">
								<input type="submit" id="botonIngresar" class="btn btn-danger btn-lg col-md-12" value="Ingresar" style="text-align: center;">
							</div>

							<div class="form-group row">
								<g:link class="btn btn-black-left col-md-6" action="regenerarContrasenia">Olvidé mi contraseña!</g:link>
								<g:link class="btn btn-black-right col-md-6" action="regenerarContrasenia">Reactivar una cuenta antigua</g:link>
							</div>
					    </g:form>					
					</div>

					<div class="col-md-1"></div>
				</div>
			</div>			

			<div class="col-md-3"></div>
		</div>
        </div>

    </body>
</html>
