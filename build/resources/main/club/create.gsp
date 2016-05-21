<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.new.label" args="['club']" /></title>
    </head>
    <body>
        <div id="create-club" class="content scaffold-create" role="main">
            <h1><g:message code="default.new.label" args="['club']" /></h1>
			<br/><br/>
	    <div class="container" align="center">
		    <g:form action="create">
			<div class="form-group" align="center">
				<label for="nombreInput">Nombre</label>
				<input class="form-control" type="text" name="nombre" id="nombreInput" placeholder="Nombre" style="width: 30%" required>
			</div> 
			<input type="submit" class="btn btn-default" value="${message(code: 'default.create')}" align="center" style="font-weight: bold;">
		    </g:form>
	    </div>

        </div>
    </body>
</html>
