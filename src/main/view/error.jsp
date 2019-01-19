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
		<title>Errore Riscontrato - MyAssistance</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" href="./assets/css/bootstrap.min.css">
		<script src="./assets/js/jquery-3.3.1.min.js"></script>
		<script src="./assets/js/bootstrap.min.js"></script>
		<!-- End -->
		
		<link rel="stylesheet" href="./assets/css/style.css">
		<script src="./assets/js/script.js"></script>
	</head>
	<body>
		<div class="page">
			<%@include file="../include/header.jsp" %>
			<div class="content" style="flex-direction: column; padding: 16px">
				<h3>Errore Riscontrato</h3>
				<br>
				<h5>Qui andrà la descrizione dell'errore passata dalla Servlet.</h5>
				<br>
				<a href="javascript:window.history.back();">Torna alla pagina precedente.</a>
				<br>
			</div>
		</div>
		<%@include file="../include/footer.jsp" %>
	</body>
</html>