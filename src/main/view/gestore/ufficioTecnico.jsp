<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 16/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Gestione Uffici Tecnici - MyAssistance</title>

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
					<div class="alert-list accordion" id="accordion_parent_new">
						<div class="card">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_new"><h5 class="mt-0 mb-1">&#43; Aggiungi Nuovo Ufficio Tecnico</h5></a>
								<form novalidate onsubmit="return utilities.validate(this)" method="post" id="collapse_new" class="collapse" data-parent="#accordion_parent_new">
									<input type="hidden" name="field-id" value="-1">
									<br>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Nome</span>
										</div>
										<input type="text" name="field-name" class="form-control" placeholder="Inserire qui il nome dell'ufficio tecnico" pattern="^.{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Telefono</span>
										</div>
										<input type="text" name="field-tel" class="form-control" placeholder="Inserire qui il contatto telefonico dell'ufficio in questione" pattern="^\+{0,1}\d{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Email</span>
										</div>
										<input type="text" name="field-email" class="form-control" placeholder="Inserire qui l'email dell'ufficio in questione" pattern="^[^@]{1,}@[^\.]{1,}(\.[^\.]{1,}){1,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Ubicazione</span>
										</div>
										<input type="text" name="field-location" class="form-control" placeholder="Inserire qui l'ubicazione dell'ufficio in questione" pattern="^.{3,}$">
									</div>
									<div>
										<button type="submit" class="btn btn-success">Aggiungi</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<br>
					<hr>
					<h3>Lista Uffici Tecnici</h3>
					<br>
					<div class="alert-list accordion" id="accordion_parent">
						<div class="card">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_0"><h5 class="mt-0 mb-1">Ufficio Tecnico Di Salerno</h5></a>
								<form novalidate onsubmit="return utilities.validate(this)" method="post" id="collapse_0" class="collapse" data-parent="#accordion_parent">
									<input type="hidden" name="field-id" value="1">
									<br>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Nome</span>
										</div>
										<input type="text" name="field-name" class="form-control" placeholder="Inserire qui il nome dell'ufficio tecnico" pattern="^.{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Telefono</span>
										</div>
										<input type="text" name="field-tel" class="form-control" placeholder="Inserire qui il contatto telefonico dell'ufficio in questione" pattern="^\+{0,1}\d{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Email</span>
										</div>
										<input type="text" name="field-email" class="form-control" placeholder="Inserire qui l'email dell'ufficio in questione" pattern="^[^@]{1,}@[^\.]{1,}(\.[^\.]{1,}){1,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Ubicazione</span>
										</div>
										<input type="text" name="field-location" class="form-control" placeholder="Inserire qui l'ubicazione dell'ufficio in questione" pattern="^.{3,}$">
									</div>
									<div>
										<button type="submit" class="btn btn-light">Applica Modifiche</button>
										<button type="button" class="btn btn-danger" onclick="ufficioTecnico.elimina('submit-dialog')">Elimina</button>
									</div>
								</form>
							</div>
						</div>
						<div class="card">
							<div class="media-body">
								<a href="#" data-toggle="collapse" data-target="#collapse_1"><h5 class="mt-0 mb-1">Ufficio Tecnico Di Avellino</h5></a>
								<form novalidate onsubmit="return utilities.validate(this)" method="post" id="collapse_1" class="collapse" data-parent="#accordion_parent">
									<input type="hidden" name="field-id" value="2">
									<br>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Nome</span>
										</div>
										<input type="text" name="field-name" class="form-control" placeholder="Inserire qui il nome dell'ufficio tecnico" pattern="^.{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Telefono</span>
										</div>
										<input type="text" name="field-tel" class="form-control" placeholder="Inserire qui il contatto telefonico dell'ufficio in questione" pattern="^\+{0,1}\d{3,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Email</span>
										</div>
										<input type="text" name="field-email" class="form-control" placeholder="Inserire qui l'email dell'ufficio in questione" pattern="^[^@]{1,}@[^\.]{1,}(\.[^\.]{1,}){1,}$">
									</div>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Ubicazione</span>
										</div>
										<input type="text" name="field-location" class="form-control" placeholder="Inserire qui l'ubicazione dell'ufficio in questione" pattern="^.{3,}$">
									</div>
									<div>
										<button type="submit" class="btn btn-light">Applica Modifiche</button>
										<button type="button" class="btn btn-danger" onclick="ufficioTecnico.elimina('submit-dialog')">Elimina</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<%@include file="../include/nav-right.jsp" %>
			</div>
		</div>
		<%@include file="../include/footer.jsp" %>
		<%@include file="../include/error-dialog.jsp" %>
		<%@include file="../include/submit-dialog.jsp" %>
		<div style="display: none">
			<div id="elimina-ufficio" method="post">
				Sei sicuro di voler eliminare questo ufficio tecnico? Questa azione è irreversibile!
				<input type="hidden" name="elimina-ufficio-id">
			</div>
		</div>
	</body>
</html>