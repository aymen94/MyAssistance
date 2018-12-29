<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 29/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Pagina Principale - MyAssistance</title>
		<link rel="stylesheet" href="assets/css/style.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="page">
			<div class="header">
				<div>Università Degli Studi Di Salerno</div>
				<div>
					<a class="btn btn-light">Accedi</a>
					<a class="btn btn-light">Registrati</a>
				</div>
			</div>
			<div class="content">
				<div style="max-width: 320px">
					<h4>Funzionalità:</h4>
					<button type="button" class="btn">Effettua Segnalazione</button>
					<button type="button" class="btn">Visualizza Segnalazioni</button>
				</div>
				<div>
					<h4>Lista Segnalazioni</h4>
					<h3>Lista Segnalazioni</h3>
					<br>
					<ul class="list-unstyled alert-list">
						<li class="media">
							<img src="assets/images/alert-open.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<h5 class="mt-0 mb-1">Microfono Non Funziona In P4</h5>
								Purtroppo questa classe è una cloaca, quindi non funziona il microfono per i professori.
							</div>
							<div class="options">
								<button type="button" class="btn btn-light">Modifica</button>
								<button type="button" class="btn btn-danger">Elimina</button>
							</div>
						</li>
						<li class="media">
							<img src="assets/images/alert-working.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<h5 class="mt-0 mb-1">Microfono Non Funziona In P4</h5>
								Purtroppo questa classe è una cloaca, quindi non funziona il microfono per i professori.
							</div>
						</li>
						<li class="media">
							<img src="assets/images/alert-resolved.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<h5 class="mt-0 mb-1">Microfono Non Funziona In P4</h5>
								Purtroppo questa classe è una cloaca, quindi non funziona il microfono per i professori.
							</div>
						</li>
						<li class="media">
							<img src="assets/images/alert-refused.png" class="mr-3 alert-image" alt="Segnalazione">
							<div class="media-body">
								<h5 class="mt-0 mb-1">Microfono Non Funziona In P4</h5>
								Purtroppo questa classe è una cloaca, quindi non funziona il microfono per i professori.
							</div>
						</li>
					</ul>
				</div>
				<div>
					<div class="card user">
						<img class="card-img-top" src="assets/images/user.png" alt="User">
						<div class="card-body">
							Sei loggato come utente <b>Guest</b>.
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div>
				<div>Università degli Studi di Salerno - Via Giovanni Paolo II, 132 - 84084 - Fisciano (SA)</div>
				<div>P.IVA 00851300657 - C.F. 80018670655</div>
			</div>
		</div>
	</body>
</html>