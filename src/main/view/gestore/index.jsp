<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 05/01/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, shrink-to-fit=no">
		<title>Pagina Principale - MyAssistance</title>
		
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
					<h3>Benvenuto Nella Piattaforma!</h3>
					Seleziona un'attività da poter svolgere, selezionandola dal menu a sinistra.
				</div>
				<%@include file="../include/nav-right.jsp" %>
			</div>
			<%@include file="../include/footer.jsp" %>
		</div>
	</body>
</html>