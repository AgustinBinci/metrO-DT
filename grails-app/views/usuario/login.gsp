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
		    <g:form action="login" role="form">

			    	<div class="form-group row">
					<div class="col-md-4">
			    		
						<div id="divInputEmail" class="has-error has-feedback">
				   			<input name="email" type="email" maxlength="50" pattern="^\w.*@\w.*([.])\w.*$" class="form-control input-lg" id="inputEmail" aria-describedby="inputEmailStatus" placeholder="E-mail..." value="${email}" required autofocus>
							<span id="spanEmail" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
							<span id="inputEmailStatus" class="sr-only">(error)</span>			
						</div>

					</div>
		  	    	</div>

			    	<div class="form-group row">
					<div class="col-md-4">
			    			
						<div id="divInputContrasenia" class="has-error has-feedback">
			   				<input name="contrasenia" type="password" maxlength="50" class="form-control input-lg" id="inputContrasenia" aria-describedby="inputContraseniaStatus" placeholder="Contraseña..." required>
							<span id="spanContrasenia" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
							<span id="inputContraseniaStatus" class="sr-only">(error)</span>
						</div>
					</div>
		  	    	</div>
				<div class="form-group row">
					<div class="col-md-4">
						<input type="submit" id="botonIngresar" class="btn btn-danger btn-lg col-md-12" value="Ingresar" style="text-align: center;">
					</div>
				</div>
		    </g:form>
		</div>
        </div>

    </body>
</html>
