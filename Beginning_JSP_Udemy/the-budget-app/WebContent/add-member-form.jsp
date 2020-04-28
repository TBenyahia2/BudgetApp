<!DOCTYPE html>
<html>
<head>
	<title>Add Member</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>The Budget App</h2>
		</div>
	</div>
	<div id="container">
		<h3>Add Member</h3>
		<form action="MemberControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD" />
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="userName" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td><label>Account ID:</label></td>
						<td><input type="text" name="account_id" /></td>						
					<tr>
					<tr>
						<td><label>Account_pin:</label></td>
						<td><input type="text" name="account_pin" /></td>						
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="MemberControllerServlet">Back to Member List</a>
		</p>	
	</div>
</body>
</html>