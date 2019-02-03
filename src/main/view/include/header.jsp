<%--
  Project: MyAssistance
  Author: Alfonso
  Date: 30/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header sticky-top">
    <div>MyAssistance</div>
    <div>
        <c:choose>
            <c:when test="${sessionScope.utente == null}">
                <a href="./utente/registrati" class="btn btn-light">Registrati</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.servletContext.contextPath}/logout"
                    class="btn btn-light">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
