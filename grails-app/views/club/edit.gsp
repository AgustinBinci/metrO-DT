<!DOCTYPE html>
<html>
    <head>
	<meta name="layout" content="main" />
        <title><g:message code="default.edit.label" args="[club?.getNombre()]" /></title>
    </head>
    <body>
        <div id="edit-club" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[club?.getNombre()]" /></h1>
			<br/><br/>
	    <div class="container" align="center">
		    <g:form action="edit">
			<div class="form-group" align="center">
				<label for="nombreInput">Nombre</label>
				<input class="form-control" type="text" name="nombre" id="nombreInput" value="${club?.getNombre()}" style="width: 30%" required>
			</div>
			<input type="hidden" name="id" value="${club?.id}">
			<input type="submit" class="btn btn-default" value="${message(code: 'default.save')}" align="center" style="font-weight: bold;">
		    </g:form>
	    </div>
        </div>
    </body>
</html>
