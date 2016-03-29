<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.new.label" args="['usuario']" /></title>
    </head>
    <body style="padding-top: 10px;">
        <div id="create-usuario" class="content scaffold-create" role="main">
            <h1><g:message code="default.new.label" args="['usuario']" /></h1>
			<br/><br/>

		    <div class="container">
			    <g:form action="create" class="formulario">

				<div class="form-group">
					<label class="control-label" for="nombreInput">Nombre</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="text" maxlength="50" name="nombre" id="nombreInput" placeholder="Nombre" value="${nombre}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div> 

				<div class="form-group">
					<label class="control-label" for="apellidoInput">Apellido</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="text" maxlength="50" name="apellido" id="apellidoInput" placeholder="Apellido" value="${apellido}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="emailInput">Email</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="email" maxlength="50" name="email" pattern="^\w.*@\w.*([.])\w.*$" id="emailInput" placeholder="Email" value="${email}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="contraseniaInput">Contraseña</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="password" maxlength="50" name="contrasenia" id="contraseniaInput" placeholder="Contraseña" value="${contrasenia}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" for="nombreDelEquipoInput">Nombre del equpo</label>
					<div class="has-success has-feedback">
						<input class="form-control" type="text" maxlength="50" name="nombreDelEquipo" id="nombreDelEquipoInput" placeholder="metrO" value="${nombreDelEquipo}" required>
						<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
						<span class="sr-only"></span>
					</div>
				</div>

				<input type="submit" class="btn btn-default" value="${message(code: 'default.create')}" align="center" style="font-weight: bold;">
			    </g:form>
		    </div>
        </div>
    </body>
</html>
