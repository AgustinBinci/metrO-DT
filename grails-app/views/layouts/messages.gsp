	<div class="container"
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
	    <g:elseif test="${flash.errorMessage}">
		<div class="message" style="color: red" role="status">${flash.errorMessage}</div>
	    </g:elseif>
	</div>
		<r:layoutResources />
