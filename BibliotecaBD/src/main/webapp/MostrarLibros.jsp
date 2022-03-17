<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Libro, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Mostrar libros</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
	integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
	crossorigin="anonymous">
<!-- CSS Files -->
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="../assets/css/light-bootstrap-dashboard.css?v=2.0.0 "
	rel="stylesheet" />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="../assets/css/demo.css" rel="stylesheet" />
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/48/2/intl/es_ALL/common.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/48/2/intl/es_ALL/util.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col-md-12">
				<div class="card strpied-tabled-with-hover">
					<div class="card-header ">
						<h2 class="card-title">Listado de libros</h2>
					</div>
					<div class="card-body table-full-width table-responsive">
						<table class="table table-hover table-striped">
							<thead>
								<th>ID</th>
								<th>Título</th>
								<th>Autor</th>
								<th>Estado</th>
							</thead>
							<tbody>

								<%
								ArrayList<Libro> lista = null;
								lista = (ArrayList<Libro>) request.getAttribute("Libros");

								for (Libro l : lista) {
									out.println("<tr><td>" + l.getId() + "</td>");
									out.println("<td>" + l.getTitulo() + "</td>");
									out.println("<td>" + l.getAutor() + "</td>");

									if (l.isPrestado()) {
										out.println("<td>Prestado</td>");
									} else {
										out.println("<td>Disponible</td>");
									}
								%>
								<td>
									<form method="post" action="modificaLibro.jsp">
										<input type="hidden" name="id" value="<%=l.getId()%>">
										<input type="hidden" name="titulo" value="<%=l.getTitulo()%>">
										<input type="hidden" name="autor" value="<%=l.getAutor()%>">
										<input type="hidden" name="prestado"
											value="<%=l.isPrestado()%>">
										<button type="submit" class="btn btn-info">Modificar</button>
									</form>
								</td>

								<td>
									<form method="post" action="borraLibro.jsp">
										<input type="hidden" name="id" value="<%=l.getId()%>">
										<input type="hidden" name="titulo" value="<%=l.getTitulo()%>">
										<input type="hidden" name="autor" value="<%=l.getAutor()%>">
										<input type="hidden" name="prestado"
											value="<%=l.isPrestado()%>">
										<button type="submit" class="btn btn-danger">Borrar</button>
									</form>
								</td>
								</tr>

								<%
								} // del for
								%>
							</tbody>
						</table>
						<div class="row justify-content-md-center">
							<div class="col-md-1 pr-1">
								<div class="form-group">
									<form method="post" action="InsertaLibros.jsp">
										<button class="btn btn-info">Insertar</button>
									</form>
								</div>
							</div>
							<div class="col-md-3 px-1">
								<div class="form-group">
									<form method="post" action="Index.jsp">
										<button class="btn btn-info">Volver a inicio</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
		integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
		crossorigin="anonymous"></script>
	<!--   Core JS Files   -->
	<script src="assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
	<script src="assets/js/core/popper.min.js" type="text/javascript"></script>
	<script src="assets/js/core/bootstrap.min.js" type="text/javascript"></script>
	<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
	<script src="../assets/js/plugins/bootstrap-switch.js"></script>
	<!--  Google Maps Plugin    -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
	<!--  Chartist Plugin  -->
	<script src="../assets/js/plugins/chartist.min.js"></script>
	<!--  Notifications Plugin    -->
	<script src="../assets/js/plugins/bootstrap-notify.js"></script>
	<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
	<script src="../assets/js/light-bootstrap-dashboard.js?v=2.0.0 "
		type="text/javascript"></script>
	<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
	<script src="../assets/js/demo.js"></script>
</body>
</html>