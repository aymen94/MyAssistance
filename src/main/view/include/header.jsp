<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 30/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header sticky-top">
	<div>Università Degli Studi Di Salerno</div>
	<div>
		<c:if test="${sessionScope.utente == null}">
			<a href="./utente/accedi" class="btn btn-light">Accedi</a>
			<a href="./utente/registrati" class="btn btn-light">Registrati</a>
		</c:if>
	</div>
</div>
