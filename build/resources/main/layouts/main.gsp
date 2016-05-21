<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Gran DT"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="mis-efectos.css"/>
    <asset:stylesheet src="errors.css"/>

    <g:layoutHead/>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">GRAN DT</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/"><g:message code="default.home"/></a></li>
					<g:if test="${session?.user}">
						<li><g:link controller="opcionesDelJuego" action="show">Juego</g:link></li>
			 			<li><g:link controller="club" action="index" >Clubes</g:link></li>
						<li><g:link controller="usuario" action="index">Participantes</g:link></li>
						<li><g:link controller="jugador" action="index">Jugadores</g:link></li>
						<li><g:link controller="equipoDeUnaFecha" action="show" params="[idUsuario: session?.user?.getId()]">Mi equipo</g:link></li>
					</g:if>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<g:if test="${session?.user}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;${session.user.nombre} ${session.user.apellido} <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><g:link controller="usuario" action="show">Ver datos</g:link></li>
								<li><g:link controller="usuario" action="edit">Editar datos</g:link></li>
								<li><g:link controller="usuario" action="cambiarContrasenia">Cambiar contraseña</g:link></li>
								<li><g:link controller="usuario" action="logout" params="[id: session?.user?.getId()]"><g:message code="default.logout"/></g:link></li>
							</ul>
						</li>
					</g:if>
					<g:else>
						<li><g:link controller="usuario" action="create">Registrarse</g:link></li>
						<li><g:link controller="usuario" action="login">Ingresar</g:link></li>
					</g:else>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

    <g:if test="${flash.errorMessage}">
		<div class="col col-md-12">
			<div class="alert alert-danger alert-dismissible container" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-alert"></span>&nbsp;<strong>Error!</strong> ${flash.errorMessage}
			</div>
		</div>
    </g:if>
    <g:if test="${flash.message}">
		<div class="col col-md-12">
			<div class="alert alert-info alert-dismissible container" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-info-sign"></span>&nbsp;<strong>Info!</strong> ${flash.message}
			</div>
		</div>
    </g:if>

    <g:layoutBody/>

    <div id="footer">

    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>
    <asset:javascript src="bootstrap.js"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="misScripts.js"/>

</body>
</html>
