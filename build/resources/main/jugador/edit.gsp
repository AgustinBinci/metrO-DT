<!DOCTYPE html>
<html>
	<head>
	   <meta name="layout" content="main" />
	   <title><g:message code="default.edit.label" args="[jugador?.getNombre()]" /></title>
	</head>
	<body>
	   <div id="edit-jugador" class="content scaffold-edit" role="main">
		  <h1><g:message code="default.edit.label" args="[jugador?.getNombre()]" /></h1>
			<br/><br/>
		<div class="container" align="center">
		    <g:form action="edit">
				<div class="form-group" align="center">
					<label for="nombreInput">Nombre</label>
					<input class="form-control" id="nombreInput" type="text" name="nombre" value="${jugador?.getNombre()}" style="width: 30%" required>
				</div>
				<div class="form-group" align="center">
					<label for="precioInput">Precio</label>
					<input class="form-control" id="precioInput" type="number" name="precio" value="${jugador?.getPrecio()}" style="width: 30%" required>
				</div>
				<div class="form-group" align="center">
					<label for="posicionInput">Posicion</label>
					<g:select class="form-control" id="posicionInput" from="${posiciones}" name="idPosicion" optionKey="id" value="${jugador?.getPosicion()?.getId()}" style="width: 30%" />
				</div>
				<div class="form-group" align="center">
					<label for="clubInput">Club</label>
					<g:select class="form-control" id="clubInput" from="${clubes}" name="idClub" optionKey="id" value="${jugador?.getClub()?.getId()}" style="width: 30%" />
				</div>
				<input type="hidden" name="id" value="${jugador?.getId()}">
				<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
		    </g:form>
		</div>
	   </div>
	</body>
</html>
