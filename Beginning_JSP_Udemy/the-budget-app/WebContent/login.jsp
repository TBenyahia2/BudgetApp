<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
 	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
    <div id = "wrapper">
        <div id ="header">
            <h2>The Budget App </h2>
        </div>
    </div>
    <div id ="container">
       	<h3>Login</h3>
       	<form action ="MemberControllerServlet" method ="POST">
       	<input type="hidden" name="command" value="validateLogin" /> 
        	<table>
           	<tbody>
				<tr>
					<td><label>Account ID:</label></td>
					<td><input type="text" name="account_id" /></td>						
				</tr>
				<tr>
					<td><label>Account Pin:</label></td>
					<td><input type="text" name="account_pin" /></td>						
				</tr>
 				<tr>
					<td><label>Username:</label></td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Login" class="save" /></td>
				</tr>
            	</tbody>
        	</table>
    	</form>
    </div>
</body>
</html>