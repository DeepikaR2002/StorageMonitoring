<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Host Details</title>

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
button :hover {
	opacity: 0.8;
}

.container {
	padding: 16px;
}

#Form {
	display : none;
}

a{
	font-size : 25px;
	cursor: pointer;
	color : blue;
	text-decoration : underline;
	font-family : calibri;
}

a:hover{
	color : purple;
}

#list{
	border : none;
	background : none;
}

#h{
	border : none;
}

</style>

<script>
function show(){
	var x = document.getElementById("Form");
	var y = document.getElementById("link");
	x.style.display="block";
	y.style.display="none";
}

</script>


</head>
<body>
	<div align=center>
		<h1>Login Successful !!</h1>
	</div>
    <br>
    
 
    <div id="link" align = center>   
       <a onclick=show()>Add a new Host</a><br><br>
       <form id="h" action="HostList" method=post>
       <button id="list" type = "submit"><a>Show Host List</a></button>
       </form>    
  </div>
     
	<br>
	<div id="Form" align=center>

		<form action=Monitor_Details method=post>

			<div class="container">
				<label for="Host"><b>Host name</b></label> <input type="text"
					placeholder="Enter Host name" name="Host" required> <label
					for="Script"><b>Script name</b></label> <input type="text"
					placeholder="Enter Script name" name="Script" required>

				<button type="submit">Submit</button>

				<a href=Login.jsp>Back</a>
			</div>
		</form>
	</div>

</body>
</html>