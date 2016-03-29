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
		    <g:form action="login" class="formulario">
			    	<div class="form-group">
		    			<label class="control-label" for="inputEmail">Email</label>
					<div id="divInputEmail" class="has-error has-feedback">
			   			<input name="email" type="email" maxlength="50" pattern="^\w.*@\w.*([.])\w.*$" class="form-control" id="inputEmail" aria-describedby="inputEmailStatus" placeholder="pegaelmetrO@gmail.com" value="${email}" required>
						<span id="spanEmail" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
						<span id="inputEmailStatus" class="sr-only">(error)</span>			
					</div>
		  	    	</div>
			    	<div class="form-group">
		    			<label for="inputContrasenia" class="control-label">Contraseña</label>
					<div id="divInputContrasenia" class="has-error has-feedback">
		   				<input name="contrasenia" type="password" maxlength="50" class="form-control" id="inputContrasenia" aria-describedby="inputContraseniaStatus" placeholder="Contraseña" required>
						<span id="spanContrasenia" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
						<span id="inputContraseniaStatus" class="sr-only">(error)</span>
					</div>
		  	    	</div>
				<div class="form-group">
					<input type="submit" class="btn btn-default" value="Ingresar" style="text-align: center;">
				</div>
		    </g:form>
		</div>
        </div>

    </body>
</html>
