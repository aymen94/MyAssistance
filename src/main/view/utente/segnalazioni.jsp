<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 03/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
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
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/nav-left-csu.jsp"%>
			<div>
				<h3>Segnalazioni Ricevute</h3>
				<br>
				<div class="alert-list accordion" id="accordion_parent">
					<c:forEach items="${segnalazioni}" var="segnalazione"
						varStatus="count">
						<form class="card" method="post">
							<input type="hidden" name="op" value="modifica-segnalazione">
							<input type="hidden" name="id" value="${segnalazione.cod}">
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
									data-parent="#accordion_parent">
									<textarea name="descrizione">${segnalazione.descrizione}</textarea>
									<c:if test="${segnalazione.stato == STATO_APERTO}">
										<div>
											<button type="submit" class="btn btn-light">Applica
												Modifiche</button>
											<button type="button" class="btn btn-danger">Elimina</button>
										</div>
									</c:if>
								</div>
							</div>
						</form>
					</c:forEach>
				</div>
			</div>
			<%@include file="../include/nav-right.jsp"%>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>