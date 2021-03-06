<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'datosDeUnaFecha.label', default: 'DatosDeUnaFecha')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="edit-datosDeUnaFecha" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<br/><br/>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.datosDeUnaFecha}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.datosDeUnaFecha}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.datosDeUnaFecha}" method="PUT">
                <g:hiddenField name="version" value="${this.datosDeUnaFecha?.version}" />
                <fieldset class="form">
                    <f:all bean="datosDeUnaFecha"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
