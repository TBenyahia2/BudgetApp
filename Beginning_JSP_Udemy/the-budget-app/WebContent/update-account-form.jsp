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
			<h2>Edit Account</h2>
		</div>
	</div>
	<div id="container">
		<h3>Edit Account</h3>
		<form action="AccountControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="accountId" value="${THE_ACCOUNT.id}" />
			<table>
				<tbody>
					<tr>
						<td><label>Account Name:</label></td>
						<td><input type="text" name="account_name"
								   value="${THE_ACCOUNT.account_name}" /></td>
					</tr>
					<tr>
						<td><label>Total Budget:</label></td>
						<td><input type="text" name="total_amount" 
						           value="${THE_ACCOUNT.total_amount}" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
								   value="${THE_ACCOUNT.email}" /></td>
					</tr>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="username"
								   value="${THE_ACCOUNT.username}" /></td>
					</tr>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name"
								   value="${THE_ACCOUNT.name}" /></td>
					</tr>
					<tr>
						<td><label>Account Pin:</label></td>
						<td><input type="text" name="pin"
								   value="${THE_ACCOUNT.pin}" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Update" class="save" /></td>
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="account-administrator-home.jsp">Back to Admin Home</a>
	</div>
</body>
</html>