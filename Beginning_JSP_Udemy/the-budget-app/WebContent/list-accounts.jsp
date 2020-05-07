<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Accounts Master</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Full Account List</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!-- Add Account button -->
			<input type="button" value="Create New Account"
				   onclick="window.location.href='create-account-form.jsp'; return false;"
				   class="create-account-button" />
			<table>
				<tr>
					<th>Account ID</th>
					<th>Account Name</th>
					<th>Budget Total</th>
					<th>AdminEmail</th>
					<th>Admin UserName</th>
					<th>Admin Name</th>
					<th>Account PIN</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempAccount" items="${ACCOUNT_LIST}">        
					<!-- set up update link for each account -->
						<c:url var="updateLink" value="AccountControllerServlet">
							<c:param name="command" value="LOAD" />
							<c:param name="accountId" value="${tempAccount.id}" />
						</c:url>
						<!-- set up delete link for each account -->
						<c:url var="deleteLink" value="AccountControllerServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="accountId" value="${tempAccount.id}" />
						</c:url>
					<tr>
						<td> ${tempAccount.id} </td>
						<td> ${tempAccount.account_name} </td>
						<td> ${tempAccount.total_amount} </td>
						<td> ${tempAccount.email} </td>
						<td> ${tempAccount.username} </td>
						<td> ${tempAccount.name} </td>
						<td> ${tempAccount.pin} </td>
						<td> <a href="${updateLink}">Update</a> 
								|
							 <a href="${deleteLink}"
							    onclick="if (!(confirm('Are you sure you want to delete this account?'))) return false">
							    Delete</a>							
						</td>				
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>