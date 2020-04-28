<!DOCTYPE html>
<html>
<head>
	<title>Update Member</title>
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
		<h3>Update Member</h3>
		<form action="MemberControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="memberId" value="${THE_MEMBER.id}" />
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="firstName"
								   value="${THE_MEMBER.name}" /></td>
					</tr>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="lastName" 
						           value="${THE_MEMBER.userName}" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"
						           value="${THE_MEMBER.email}"  /></td>
					</tr>
					<tr>
						<td><label>Account Id:</label>
						<td><input type="text" name="account_id"
						           value="${THE_MEMBER.account_id}"  /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="account-administrator-home.jsp">Back to Admin Home</a>
		</p>	
	</div>
</body>
</html>