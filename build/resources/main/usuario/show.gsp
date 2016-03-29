<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>${usuario?.getNombre() + " " + usuario?.getApellido()}</title>
    </head>
    <body>
        <div id="show-opcionesDelJuego" class="content scaffold-show" role="main">
            <h1>${usuario?.getNombre() + " " + usuario?.getApellido()}</h1>
			<br/><br/>
			<div class="container">
				<table class="table table-hover">
					<tr>
						<td><p class="texto-filas-left">Nombre</p></td>
						<td><p class="texto-filas-right">${usuario?.getNombre()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Apellido</p></td>
						<td><p class="texto-filas-right">${usuario?.getApellido()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Email</p></td>
						<td><p class="texto-filas-right">${usuario?.getEmail()}</p></td>
					</tr>
					<tr>
						<td><p class="texto-filas-left">Nombre del equipo</p></td>
						<td><p class="texto-filas-right">${usuario?.getNombreDelEquipo()}</p></td>
					</tr>
				</table>
			<div>
        </div>
    </body>
</html>
