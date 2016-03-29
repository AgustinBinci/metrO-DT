<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.new.label" args="['jugador']" /></title>
    </head>
    <body>
        <div id="create-jugador" class="content scaffold-create" role="main">
            <h1><g:message code="default.new.label" args="['jugador']" /></h1>
			<br/><br/>
		<div class="container" align="center">
		    <g:form action="create">
			<div class="form-group" align="center">
				<label for="nombreInput">Nombre</label>
				<input class="form-control" id="nombreInput" type="text" name="nombre" placeholder="Nombre" style="width: 30%" required>
			</div>
			<div class="form-group" align="center">
				<label for="precioInput">Precio</label>
				<input class="form-control" id="precioInput" type="number" name="precio" value="550000" style="width: 30%" required>
			</div>
			<div class="form-group" align="center">
				<label for="posicionInput">Posicion</label>
				<g:select class="form-control" id="posicionInput" from="${posiciones}" name="idPosicion" optionKey="id" style="width: 30%" />
			</div>
			<div class="form-group" align="center">
				<label for="clubInput">Club</label>
				<g:select class="form-control" id="clubInput" from="${clubes}" name="idClub" optionKey="id" value="${idClub}" style="width: 30%" />
			</div>
				<input type="submit" class="btn btn-default" value="${message(code: 'default.create')}" align="center" style="font-weight: bold;">
		    </g:form>
		</div>
        </div>
    </body>
</html>
