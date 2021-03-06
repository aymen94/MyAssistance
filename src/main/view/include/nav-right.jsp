<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 30/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="side">
	<div class="card user">
		<img class="card-img-top"
			src="${pageContext.servletContext.contextPath}/assets/images/user.png"
			alt="User">
		<div class="card-body">
			<c:choose>
				<c:when test="${sessionScope.utente == null}">
					Non sei loggato
				</c:when>
				<c:otherwise>
					<a style="text-transform: capitalize">${sessionScope.utente.cognome}
						${sessionScope.utente.nome}<br>
					</a>
                @${sessionScope.utente.userName}<br>
					<c:choose>
						<c:when test="${sessionScope.utente.isGestore()}">
							<c:if
								test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/utente/')}">
								<i>Passa alla modalità <a
									href="${pageContext.servletContext.contextPath}/gestore/">Gestore</a></i>.<br>
							</c:if>
							<c:if
								test="${fn:contains(requestScope['javax.servlet.forward.servlet_path'],'/gestore/')}">
								<i>Passa alla modalità <a
									href="${pageContext.servletContext.contextPath}/utente/">Utente</a></i>.<br>
							</c:if>
							<b>Sei autenticato come Gestore</b>
						</c:when>
						<c:otherwise>
							<b>Sei autenticato come CSU</b>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
