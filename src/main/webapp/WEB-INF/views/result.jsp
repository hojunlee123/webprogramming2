<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Survey Outcome</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <h1>Survey Outcome</h1>

    <div class="result-list">
        <%-- 투표율이 높은 순서대로 막대와 수치를 표시한다. --%>
        <c:forEach var="option" items="${options}">
            <div class="result-row">
                <span class="fruit-name">${option.name}</span>
                <span class="fruit-rate">${option.voteRate}%</span>
                <div class="bar-wrap">
                    <div class="bar" style="width: ${option.voteRate * 3}px;"></div>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${not empty selectedOption}">
        <p>You chose <strong>${selectedOption.name}</strong>.</p>
    </c:if>

    <p><strong>${totalVotes}</strong> participant(s) join the survey.</p>
    <a class="home-link" href="${pageContext.request.contextPath}/home">Home</a>
</div>
</body>
</html>
