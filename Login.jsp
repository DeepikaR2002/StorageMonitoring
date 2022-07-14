<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<style type="text/css">
/* Bordered form */
form {
	border: 3px solid #f1f1f1;
	width: 50%;
}

/* Full-width inputs */
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

/* Add a hover effect for buttons */
button:hover {
	opacity: 0.8;
}

.container {
	padding: 16px;
}
</style>
</head>
<body>

	<br>
	<br>
	<div align=center>

		<form action=LoginServlet method=post>

			<div class="container">
				<label for="txtName"><b>Username</b></label> <input type="text"
					placeholder="Enter Username" name="txtName" required> <label
					for="txtPwd"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="txtPwd" required>

				<button type="submit">Login</button>
			</div>
		</form>
	</div>
</body>
</html>