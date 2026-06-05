<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Favorite Fruit Survey</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <h1>Survey for Your Favorite Fruit</h1>
    <p>Click vote to join the survey.</p>
    <p>Click outcome for the result.</p>

    <div class="menu-links">
        <%-- 투표 입력 화면으로 이동한다. --%>
        <a href="${pageContext.request.contextPath}/vote">VOTE</a>

        <%-- 현재 집계 결과 화면으로 이동한다. --%>
        <a href="${pageContext.request.contextPath}/result">OUTCOME</a>
    </div>
</div>
</body>
</html>
