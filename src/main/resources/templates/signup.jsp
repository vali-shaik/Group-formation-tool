<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head></head>
<body>

<h2 align = "center">SignUp Page</h2>

<center>
<form action ="#" th:action="@{/signup}" th:object=${user} method = "POST" onSubmit ="validatePassword()">
  <label for="fname">First name:</label><br>
  <input type="text" id="fname" name="fname" required th:field="*{firstName}"><br>
  <label for="lname">Last name:</label><br>
  <input type="text" id="lname" name="lname" required th:field="*{lastName}"><br>
  <label for="bannerid">Banner Id:</label><br>
  <input type="text" id="bannerid" name="bannerid" required th:field="*{bannerId}"><br>
  <label for="emailid">Email:</label><br>
  <input type="email" id="emailid" name="emailid" required th:field="*{email}"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Min Length: 8 including UpperCase, LowerCase and Number" th:field="*{password}"><br>
  <label for="confirmpassword">Confirm Password:</label><br>
  <input type="password" id="confirmpassword" name="confirmpassword" required oninput="validatePassword()"><br><br>
  <input type="submit" value="Submit">
</form> 
<p>Already a user? <a href="login.jsp"> LogIn</a></p>
 
</center>

<script type="text/javascript">
function validatePassword() {
    const password = document.getElementById('password');
    const confirm_password = document.getElementById('confirmpassword');

    if (password.value === confirm_password.value) {
    	confirm_password.setCustomValidity('');
    } 
    else {
    	confirm_password.setCustomValidity('Passwords do not match');
    }
}
</script>
</body>
</html>
