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
	    <div class="container" align="center">
		    <g:form action="edit">
			<div class="form-group" align="center">
				<label for="nombreInput">Nombre</label>
				<input class="form-control" type="text" name="nombre" id="nombreInput" value="${usuario?.getNombre()}" style="width: 30%" required>
			</div> 
			<div class="form-group" align="center">
				<label for="apellidoInput">Apellido</label>
				<input class="form-control" type="text" name="apellido" id="apellidoInput" value="${usuario?.getApellido()}" style="width: 30%" required>
			</div>
			<div class="form-group" align="center">
				<label for="emailInput">Email</label>
				<input class="form-control" type="email" name="email" id="emailInput" value="${usuario?.getEmail()}" style="width: 30%" required>
			</div>
			<div class="form-group" align="center">
				<label for="equipoInput">Nombre del equipo</label>
				<input class="form-control" type="text" name="nombreDelEquipo" id="equipoInput" value="${usuario?.getNombreDelEquipo()}" style="width: 30%" required>
			</div>
			<input type="hidden" name="id" value="${usuario?.getId()}">
			<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
		    </g:form>
	    </div>
        </div>
    </body>
</html>
