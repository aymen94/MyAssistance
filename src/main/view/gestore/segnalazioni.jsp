<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 03/01/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Segnalazioni Ricevute - MyAssistance</title>
		
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
				<%@include file="../include/nav-left-gestore.jsp" %>
				<div>
					<h3>Segnalazioni Ricevute</h3>
					<br>
					<div class="alert-list accordion" id="accordion_parent">
						<div class="card">
							<img src="../assets/images/alert-open.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_0"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_0" class="collapse" data-parent="#accordion_parent">
									Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									<div class="alert-buttons">
										<button type="button" class="btn btn-light" onclick="segnalazioni.inoltra('submit-dialog', 1)">Inoltra</button>
										<button type="button" class="btn btn-danger" onclick="segnalazioni.rifiuta('submit-dialog', 1)">Rifiuta</button>
									</div>
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-working.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_1"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_1" class="collapse" data-parent="#accordion_parent">
									Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
									<div class="alert-buttons">
										<button type="button" class="btn btn-success" onclick="segnalazioni.segnaRisolta('submit-dialog', 1)">Segna Risolta</button>
									</div>
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-refused.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_2"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_2" class="collapse" data-parent="#accordion_parent">
									Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
								</div>
							</div>
						</div>
						<div class="card">
							<img src="../assets/images/alert-resolved.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_3"><h5 class="mt-0 mb-1">Microfono non funziona in P4</h5></a>
								<div id="collapse_3" class="collapse" data-parent="#accordion_parent">
									Questa icona indica una segnalazione ancora aperta. Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@include file="../include/nav-right.jsp" %>
			</div>
		</div>
		<%@include file="../include/footer.jsp" %>
		<%@include file="../include/submit-dialog.jsp" %>
		<div style="display: none">
			<div id="inoltra-segnalazione">
				Scegli l'ufficio tecnico a cui vuoi assegnare la risoluzione di questa segnalazione ricevuta.
				<input type="hidden" name="inoltra-id">
				<select name="inoltra-ufficio" class="custom-select" style="margin-top: 16px">
					<option value="1">Ripara S.R.L.</option>
					<option value="2">AlbaFire S.p.a.</option>
					<option value="3">Sne srl</option>
				</select>
			</div>
			<div id="rifiuta-segnalazione" method="post">
				Sei sicuro di voler rifiutare questa segnalazione? Questa azione è irreversibile!
				<input type="hidden" name="inoltra-id">
			</div>
			<div id="segna-risolta" method="post">
				Sei sicuro di voler segnare questa segnalazione come risolta? Questa azione è irreversibile!
				<input type="hidden" name="inoltra-id">
			</div>
		</div>
	</body>
</html>