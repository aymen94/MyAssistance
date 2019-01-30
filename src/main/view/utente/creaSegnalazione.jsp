<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 02/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
<title>Crea Segnalazione - MyAssistance</title>

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
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/nav-left-csu.jsp"%>
			<div>
				<h3>Creazione Segnalazione</h3>
				<br>
				<c:choose>
					<c:when test="${inserita != null}">
						<div class="alert alert-success" role="alert">La
							segnalazione è stata inserita con successo.</div>
					</c:when>
					<c:otherwise>
						<form novalidate onsubmit="return utilities.validate(this)"
							method="post">
							<div class="form-group">
								<label for="field-title">Titolo</label> <input type="text"
									class="form-control" name="field-title" id="field-title"
									aria-describedby="field-title-username"
									placeholder="Titolo segnalazione" pattern="^.{1,50}$"
									maxlength="50"> <small id="field-title-hint"
									class="form-text text-muted">Questo è il titolo della
									segnalazione che stai effettuando.</small>
							</div>
							<div class="form-group">
								<label for="field-descr">Descrizione</label>
								<textarea class="form-control" name="field-descr"
									id="field-descr" aria-describedby="field-descr-hint"
									placeholder="Descrivi qui dettagliatamente la segnalazione"
									maxlength="4096" style="min-height: 192px"></textarea>
								<small id="field-descr-hint" class="form-text text-muted">Descrivi
									qui, in modo dettagliato, la tua segnalazione.</small>
							</div>
							<div class="form-group">
								<label for="field-type">Tipologia</label> <select
									class="form-control" name="field-type" id="field-type"
									aria-describedby="field-type">
									<c:forEach items="${tipologie}" var="tipologia">
										<option value="${tipologia.id}">${tipologia.nome}</option>
									</c:forEach>
								</select> <small id="field-type-hint" class="form-text text-muted">Devi
									inserire una tipologia conforme alla segnalazione che stai
									redigendo.</small>
							</div>
							<br>
							<div
								style="margin: 0 auto; max-width: 240px; text-align: justify">
								<button type="submit" class="btn btn-success">Invia</button>
								<button type="reset" class="btn btn-secondary">Reset</button>
								<span style="display: inline-block; width: 100%"></span>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
			<%@include file="../include/nav-right.jsp"%>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
	<%@include file="../include/error-dialog.jsp"%>
</body>
</html>