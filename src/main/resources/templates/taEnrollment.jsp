<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
<h1 align="center">TA enrollment</h1>
<center>
<form th:action="@{/enrollTa}" th:object="${user}" method = "post">
  <label >Enter the Banner Id of the user:</label><br/>
   <input type="text" class="form-control" th:field="*{bannerId}">
   <input type="submit" value="Submit">
</form>
</center>

</body>
</html>