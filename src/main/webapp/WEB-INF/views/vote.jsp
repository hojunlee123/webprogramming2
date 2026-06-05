<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Vote</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <h1>Select your favorite fruit.</h1>

    <form action="${pageContext.request.contextPath}/vote" method="post">
        <%-- 컨트롤러에서 넘겨준 과일 목록을 라디오 버튼으로 출력한다. --%>
        <c:forEach var="option" items="${options}">
            <label class="radio-item">
                <input type="radio" name="fruitId" value="${option.id}" required>
                ${option.name}
            </label>
        </c:forEach>

        <button type="submit">VOTE</button>
    </form>
</div>
</body>
</html>
