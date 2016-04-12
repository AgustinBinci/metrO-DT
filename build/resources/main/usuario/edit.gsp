<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.edit.label" args="['usuario']" /></title>
    </head>
    <body>
        <div id="edit-usuario" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="['usuario']" /></h1>
			<br/><br/>

		    <div class="container">
			    <g:form action="edit" role="form">

				    	<div class="form-group row">
						<div class="col-md-4">
				    		
							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="text" maxlength="50" name="nombre" id="nombreInput" placeholder="Nombre..." value="${usuario?.nombre}" required autofocus>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>
					
						</div>
					</div>

				    	<div class="form-group row">
						<div class="col-md-4">
				    		
							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="text" maxlength="50" name="apellido" id="apellidoInput" placeholder="Apellido..." value="${usuario?.apellido}" required>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>

						</div>
					</div>

				    	<div class="form-group row">
						<div class="col-md-4">
				    		
							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="email" maxlength="50" name="email" pattern="^\w+[a-z,A-Z,0-9,.,-,_]*@\w+[a-z,A-Z,0-9,.,-,_]*([.])\w.*$" id="emailInput" placeholder="Email..." value="${usuario?.email}" required>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>

						</div>
					</div>

				    	<div class="form-group row">
						<div class="col-md-4">
				    						
							<div class="has-success has-feedback">
								<input class="form-control input-lg" type="text" maxlength="50" name="nombreDelEquipo" id="nombreDelEquipoInput" placeholder="Nombre del equipo..." value="${usuario?.nombreDelEquipo}" required>
								<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
								<span class="sr-only"></span>
							</div>
				
						</div>
					</div>

				    	<div class="form-group row">
						<div class="col-md-4">				    		
							<input type="submit" class="btn btn-danger btn-lg col-md-12" value="${message(code: 'default.update')}">			
						</div>
					</div>

			    </g:form>

		    </div>

        </div>
    </body>
</html>
