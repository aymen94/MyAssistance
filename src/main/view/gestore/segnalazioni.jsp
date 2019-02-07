<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 03/01/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
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
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/nav-left-gestore.jsp"%>
			<div>
				<h3>Segnalazioni Ricevute</h3>
				<br>
				<div class="alert-list accordion" id="accordion_parent">
					<c:forEach items="${segnalazioni}" var="segnalazione"
						varStatus="count">
						<div class="card">
							<c:choose>
								<c:when test="${segnalazione.stato == STATO_APERTO}">
									<img src="../assets/images/alert-open.png"
										class="mr-3 alert-image" alt="Segnalazione Aperta">
								</c:when>
								<c:when test="${segnalazione.stato == STATO_RIFIUTATO}">
									<img src="../assets/images/alert-refused.png"
										class="mr-3 alert-image" alt="Segnalazione Rifiutata">
								</c:when>
								<c:when test="${segnalazione.stato == STATO_ASSEGNATO}">
									<img src="../assets/images/alert-working.png"
										class="mr-3 alert-image" alt="Segnalazione Assegnata">
								</c:when>
								<c:otherwise>
									<img src="../assets/images/alert-resolved.png"
										class="mr-3 alert-image" alt="Segnalazione Risolta">
								</c:otherwise>
							</c:choose>
							<div class="media-body">
								<h5 class="mt-0 mb-1">
									<a href="#" data-toggle="collapse"
										data-target="#collapse_${count.count}">${segnalazione.titolo}</a>
								</h5>
								<div id="collapse_${count.count}" class="collapse"
									data-parent="#accordion_parent">${segnalazione.descrizione}
									<c:choose>
										<c:when test="${segnalazione.stato == STATO_APERTO}">
											<div class="alert-buttons">
												<button type="button" class="btn btn-light"
													onclick="segnalazioni.inoltra('submit-dialog', ${segnalazione.cod})">Inoltra</button>
												<button type="button" class="btn btn-danger"
													onclick="segnalazioni.rifiuta('submit-dialog', ${segnalazione.cod})">Rifiuta</button>
											</div>
										</c:when>
										<c:when test="${segnalazione.stato == STATO_ASSEGNATO}">
											<div class="alert-buttons">
												<button type="button" class="btn btn-success"
													onclick="segnalazioni.segnaRisolta('submit-dialog', ${segnalazione.cod})">Segna
													Risolta</button>
											</div>
										</c:when>
									</c:choose>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<%@include file="../include/nav-right.jsp"%>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
	<%@include file="../include/error-dialog.jsp"%>
	<%@include file="../include/submit-dialog.jsp"%>
	<div style="display: none">
		<div id="inoltra-segnalazione">
			Scegli l'ufficio tecnico a cui vuoi assegnare la risoluzione di
			questa segnalazione ricevuta. <input type="hidden" name="codice">
			<label for="ufficio-select" style="display: none">Ufficio
				Tecnico</label> <input type="hidden" name="op" value="inoltra"> <select
				id="ufficio-select" name="ufficio" class="custom-select"
				style="margin-top: 16px">
				<c:forEach items="${uffici}" var="ufficio" varStatus="count">
					<option value="${ufficio.id}">${ufficio.nome}</option>
				</c:forEach>
			</select>
		</div>
		<div id="rifiuta-segnalazione">
			Sei sicuro di voler rifiutare questa segnalazione? Questa azione è
			irreversibile! <input type="hidden" name="codice"> <input
				type="hidden" name="op" value="rifiuta">
			<div class="form-group">
				<label for="rifiuta-motivation" style="display: none">Motivazione</label>
				<textarea class="form-control" id="rifiuta-motivation"
					class="motivation"
					placeholder="Inserisci qui la motivazione del rifiuto"
					style="min-height: 128px"></textarea>
			</div>
		</div>
		<div id="segna-risolta">
			Sei sicuro di voler segnare questa segnalazione come risolta? Questa
			azione è irreversibile! <input type="hidden" name="codice"> <input
				type="hidden" name="op" value="risolvi">
		</div>
	</div>
</body>
</html>