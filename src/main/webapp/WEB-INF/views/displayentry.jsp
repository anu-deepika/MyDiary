<%@ page isELIgnored="false"%>
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
	<br />
	<br />

	<h1>
		welcome <span style="color: SteelBlue">${user.username}</span>
	</h1>
	<a href="./signout"
		style="color: red; float: right; font-size: 25px; font-weight: bold; margin-right: 20px;">Signout</a>

	<div class="bodypart">
		<div class="bodypart1">
			<img src="<c:url value="/resources/images/diary.jpg.jpeg"/>">
		</div>

		<div class="bodypart2">
			<h1>VIEW ENTRY</h1>
			<br />
			<br />
			<br />

			<table>
				<tr>
					<td style="font-weight: bold; padding-right: 10px;">Date:</td>
					<td>${entry.entrydate}</td>
				</tr>
				<tr>
					<td
						style="font-weight: bold; padding-right: 10px; vertical-align: top;">Description:</td>
					<td>${entry.description}</td>
				</tr>
			</table>

			<br />
			<br />
			<br />

			<!-- Updated link from ./userhome to ./home to match HomeController -->
			<a href="./home"><button type="button" class="addbtn">BACK
					TO HOME</button></a>

		</div>
	</div>

</body>
</html>