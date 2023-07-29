<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">
    username: <input type="text" name="username"/> age: <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>
</body>
</html>

<%--<

http://localhost:8080/servlet-mvc/members/new-form
form action="save" method="post"> 에서

/save 라고 적을 경우에서
http://localhost:8080/save
로 접근한다

sava 로 적게되면
http://localhost:8080/servlet-mvc/members/save
로 접근한다


--%>