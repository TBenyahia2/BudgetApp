<!DOCTYPE html>
<html>
<head>
	<title>Create Account</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Create Account</h2>
		</div>
	</div>
	<div id="container">
		<h3>Create Account</h3>
		<form action="AccountControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD" />
			<table>
				<tbody>
					<tr>
						<td><label>Account Name:</label></td>
						<td><input type="text" name="account_name" /></td>
					</tr>
					<tr>
						<td><label>Total Budget:</label></td>
						<td><input type="text" name="total_amount" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td><label>Account Pin:</label></td>
						<td><input type="text" name="pin" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="welcome-page.jsp">Back to Account Selection</a>
	</div>
</body>
</html>