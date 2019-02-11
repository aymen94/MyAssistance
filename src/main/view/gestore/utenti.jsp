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
<title>Lista Utenti - MyAssistance</title>

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
				<h3>Lista Utenti</h3>
				<br>
				<div class="container">
					<c:forEach items="${users}" var="user">
						<div class="row pt-3 pb-3 border">
							<div class="col">
								<b>${user.userName}</b> - ${user.nome} ${user.cognome}
							</div>
							<c:choose>
								<c:when test="${user.isGestore()}">
									<div class="col-md-auto">
										<span class="btn badge badge-info">Gestore</span>
									</div>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${user.dataSospensione != null}">
											<div class="col-md-auto">
												<span class="btn badge badge-secondary">Sospeso</span>
											</div>
										</c:when>
										<c:otherwise>
											<form class="col-md-auto" action="./sospendiUtente"
												method="post">
												<input type="hidden" name="id" value="${user.id}">
												<button type="submit" class="btn badge badge-warning">Sospendi</button>
											</form>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>
			</div>
			<%@include file="../include/nav-right.jsp"%>
		</div>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>