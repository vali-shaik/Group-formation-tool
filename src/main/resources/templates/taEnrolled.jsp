<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <title>Catme</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1 align="center">TA enrollment</h1>
	<center>
    <p th:text="'Course Id: ' + ${user.courseId}" />
    <p th:text="'Banner Id: ' + ${user.bannerId}" />
    <p th:text="${message}" />
    </center>
</body>
</html>