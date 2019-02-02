<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 30/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="side">
	<div class="card user">
		<img class="card-img-top"
			src="${pageContext.servletContext.contextPath}/assets/images/user.png"
			alt="User">
		<div class="card-body">
			Sei loggato come utente
			<c:choose>
				<c:when test="${sessionScope.utente == null}">
					<b>Guest</b>.
				</c:when>
				<c:when test="${sessionScope.utente.isGestore()}">
					<b>Gestore</b>.
				</c:when>
				<c:otherwise>
					<b>CSU</b>.
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
