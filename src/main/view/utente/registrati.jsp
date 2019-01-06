<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 30/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Pagina Principale - MyAssistance</title>

		<!-- Bootstrap -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<script src="assets/js/jquery-3.3.1.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<!-- End -->

		<link rel="stylesheet" href="assets/css/style.css">
		<script src="assets/js/script.js"></script>
	</head>
	<body>
		<div class="page">
			<%@include file="../include/header.jsp" %>
			<div class="content">
				<%@include file="../include/nav-left-csu.jsp" %>
				<div>
					<h3>Registrazione Utente</h3>
					<br>
					<form novalidate onsubmit="return utilities.validate(this)" method="post">
						<div class="form-group">
							<label for="field-username">Username</label>
							<input type="text" class="form-control" name="field-username" id="field-username" aria-describedby="field-password-username" placeholder="mario.rossi" pattern="^\S{1,20}$">
							<small id="field-username-hint" class="form-text text-muted">Questa è l'username che userai per accedere.</small>
						</div>
						<div class="form-group">
							<label for="field-password">Password</label>
							<input type="password" class="form-control" name="field-password" id="field-password" aria-describedby="field-password-hint" placeholder="&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;" pattern="^.{1,100}$" data-same-first="1">
							<small id="field-password-hint" class="form-text text-muted">Questa è la password che userai per accedere.</small>
						</div>
						<div class="form-group">
							<label for="field-repeat">Ripeti Password</label>
							<input type="password" class="form-control" id="field-repeat" aria-describedby="field-repeat-hint" placeholder="&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;" pattern="^.{1,100}$" data-same-second="1">
							<small id="field-repeat-hint" class="form-text text-muted">Devi ripetere la password per motivi di sicurezza.</small>
						</div>
						<div class="form-group">
							<label for="field-email">Email</label>
							<input type="text" class="form-control" name="field-email" id="field-email" aria-describedby="field-email-hint" placeholder="mario@rossi.it" pattern="^[^@]{1,}@[^\.]{1,}(\.[^\.]{1,}){1,}$">
							<small id="field-email-hint" class="form-text text-muted">Inserisci qui l'email per inviarti eventuali comunicazioni.</small>
						</div>
						<div class="form-group">
							<label for="field-name">Nome</label>
							<input type="text" class="form-control" name="field-name" id="field-name" aria-describedby="field-name-username" placeholder="Mario" pattern="^.{1,50}$">
							<small id="field-name-hint" class="form-text text-muted">Devi inserire il tuo nome reale.</small>
						</div>
						<div class="form-group">
							<label for="field-surname">Cognome</label>
							<input type="text" class="form-control" name="field-surname" id="field-surname" aria-describedby="field-surname-username" placeholder="Rossi" pattern="^.{1,50}$">
							<small id="field-surname-hint" class="form-text text-muted">Devi inserire il tuo cognome reale.</small>
						</div>
						<div>
							<label>Sesso</label>
							<div class="form-check">
								<input class="form-check-input" type="radio" value="male" id="field-gender-male" name="field-gender">
								<label class="form-check-label" for="field-gender-male">Uomo</label>
								<br>
								<input class="form-check-input" type="radio" value="female" id="field-gender-female" name="field-gender">
								<label class="form-check-label" for="field-gender-female">Donna</label>
							</div>
						</div>
						<br>
						<div style="margin: 0 auto; max-width: 240px; text-align: justify">
							<button type="submit" class="btn btn-success">Registrati</button>
							<button type="reset" class="btn btn-secondary">Reset</button>
							<span style="display: inline-block; width: 100%"></span>
						</div>
					</form>
				</div>
				<%@include file="../include/nav-right.jsp" %>
			</div>
		</div>
		<%@include file="../include/footer.jsp" %>
		<%@include file="../include/error-dialog.jsp" %>
	</body>
</html>
