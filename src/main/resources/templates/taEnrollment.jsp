<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
<h1 align="center">TA enrollment</h1>
<center>
<form th:action="@{/taEnrollment}" th:object=${user} method = "POST">
  <label for="bannerid">Enter the Banner Id of the user:</label><br>
  <input type="text" name="bannerid" id="bannerid" required th:field="*{bannerId}">
  <input type="submit" value="Submit">
</form>
</center>

</body>
</html>