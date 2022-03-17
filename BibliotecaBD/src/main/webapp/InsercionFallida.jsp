<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Inserción fallida</title>
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
			<div class="col-md-5">
				<div class="card">
					<div class="card-header text-center">
						<h4 class="card-title">Insertar</h4>
					</div>
					<div class="card-body">
						<div class="row justify-content-md-center">
							<p>Ha sucedido un error al insertar el libro.</p>
							<p>Comprueba que el ID introducido no existe.</p>
						</div>
						<div class="row justify-content-md-center mt-4">
							<div class="col-md-3 pr-1">
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