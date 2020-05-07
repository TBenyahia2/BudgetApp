<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome Page</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Welcome to The Budget App!</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!-- Add Account button -->
			<input type="button" value="Create New Account"
				   onclick="window.location.href='create-account-form.jsp'; return false;"
				   class="create-account-button" />
			or Select your Account from the list below
			<table>
			<tr>
				<th>Account ID</th>
				<th>Account Name</th>
				<th>AdminEmail</th>
				<th>Admin Name</th>
				<th>Login</th>
			</tr>
			<c:forEach var="tempAccount" items="${ACCOUNT_LIST}">        
				<!-- set up update link for each account -->
					<c:url var="loginLink" value="AccountControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="accountId" value="${tempAccount.id}" />
					</c:url>
				<tr>
					<td> ${tempAccount.id} </td>
					<td> ${tempAccount.account_name} </td>
					<td> ${tempAccount.email} </td>
					<td> ${tempAccount.name} </td>
					<td> <a href="${loginLink}">Login</a> 					
					</td>				
				</tr>
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>