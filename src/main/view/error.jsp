<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 16/01/2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
<title>Errore Riscontrato - MyAssistance</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/bootstrap.min.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/bootstrap.min.js"></script>
<!-- End -->

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/style.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/script.js"></script>
</head>
<body>
	<div class="page">
		<%@include file="./include/header.jsp"%>
		<div class="content" style="flex-direction: column; padding: 16px">
			<h3>Errore Riscontrato</h3>
			<br>
			<c:choose>
				<c:when test="${msgError != null}">
					<h5>${msgError}</h5>
				</c:when>
				<c:otherwise>
					<h5>Errore non gestito.</h5>
				</c:otherwise>
			</c:choose>
			<br> <a href="javascript:window.history.back();">Torna alla
				pagina precedente.</a> <br>
		</div>
	</div>
	<%@include file="./include/footer.jsp"%>
</body>
</html>
