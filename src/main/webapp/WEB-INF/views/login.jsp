<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyDiary App Homepage</title>
<link rel="stylesheet"
	href="<c:url value="/resources/styles/style.css"/>">
</head>
<body>

	<div class="header">

		<div class="first">
			<img src="<c:url value="/resources/images/diary.jpg.jpeg"/>"
				width="60" height="60">
		</div>
		<div class="second">MyDiary App</div>

	</div>

	<br />
	<br />

	<hr />


	<div class="bodypart">

		<div class="bodypart1">
			<img src="<c:url value="/resources/images/diary.jpg.jpeg"/>">
		</div>

		<div class="bodypart2">
			<h1>LOGIN HERE</h1>
			<c:if test="${not empty error}">
				<div style="color: red; margin-bottom: 15px; font-weight: bold;">${error}</div>
			</c:if>

			<c:if test="${not empty message}">
				<div style="color: green; margin-bottom: 15px; font-weight: bold;">${message}</div>
			</c:if>
			<br /> <br /> <br />
			<form action="processLogin" method="POST">
				<label>username</label> <input type="text" name="username"
					class="formcontrol"><br /> <br /> <label>password</label>
				<input type="password" name="password" class="formcontrol"><br />
				<br /> <br />
				<button type="submit">LOGIN</button>
			</form>
			<br /> <br /> New User? <a href="register">Register</a> here
		</div>




	</div>

</body>
</html>