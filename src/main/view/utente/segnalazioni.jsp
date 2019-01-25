<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 03/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Segnalazioni Effettuate - MyAssistance</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" href="../assets/css/bootstrap.min.css">
		<script src="../assets/js/jquery-3.3.1.min.js"></script>
		<script src="../assets/js/bootstrap.min.js"></script>
		<!-- End -->
		
		<link rel="stylesheet" href="../assets/css/style.css">
		<script src="../assets/js/script.js"></script>
	</head>
	<body>
		<div class="page">
			<%@include file="../include/header.jsp" %>
			<div class="content">
				<%@include file="../include/nav-left-csu.jsp" %>
				<div>
					<h3>Segnalazioni Ricevute</h3>
					<br>
					<div class="alert-list accordion" id="accordion_parent">
						<div class="card">
							<img src="../assets/images/alert-open.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_0"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_0" class="collapse" data-parent="#accordion_parent">
									<textarea>
										Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									</textarea>
									<div>
										<button type="button" class="btn btn-light">Applica Modifiche</button>
										<button type="button" class="btn btn-danger">Elimina</button>
									</div>
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-resolved.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_1"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_1" class="collapse" data-parent="#accordion_parent">
									<textarea style="pointer-events: none">
										Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									</textarea>
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-working.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_2"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_2" class="collapse" data-parent="#accordion_parent">
									<textarea style="pointer-events: none">
										Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									</textarea>
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-refused.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_3"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_3" class="collapse" data-parent="#accordion_parent">
									<textarea style="pointer-events: none">
										Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@include file="../include/nav-right.jsp" %>
			</div>
			<%@include file="../include/footer.jsp" %>
		</div>
	</body>
</html>