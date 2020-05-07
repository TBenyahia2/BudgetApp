<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>The Budget App</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Account Member Home</h2>
		</div>
	</div>
	<h4>Logged in to account: ${THE_ACCOUNT.account_name}</h4>
	<h4>Logged in as        : ${THE_MEMBER.userName}</h4>
	<h4>Total Budget        : $${THE_ACCOUNT.total_amount}</h4>
	<div id="container">
		<div id="content">
		<input type="button" value="Add Transaction" 
		onclick="window.location.href='add-transaction-form.jsp'; return false;"
		class="add-transaction-button" />
		<table>
			<h3>Account Transaction List</h3>
			<tr>
				<th>ID</th>
				<th>Account Id</th>
				<th>Member Id</th>
				<th>Amount</th>
				<th>Name</th>
				<th>Date</th>
				<th>Action</th>
			</tr>
			<c:forEach var="tempTransaction" items="${TRANSACTION_LIST}">
				<!-- Set Up link for each Transaction row -->
				<c:url var="updateLink" value="TransactionControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="transactionId" value="${tempTransaction.id}" />						
				</c:url>
				<c:url var="deleteLink" value="TransactionControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="transactionId" value="${tempTransaction.id}" />	
				</c:url>
				<tr>
					<td> ${tempTransaction.id} </td>
					<td> ${tempTransaction.account_id} </td>
					<td> ${tempTransaction.member_id} </td>
					<td> ${tempTransaction.amount} </td>
					<td> ${tempTransaction.name} </td>
					<td> ${tempTransaction.date} </td>
					<td> <a href="${updateLink}">Update</a>
							|
						  <a href="${deleteLink}"
						  	 onclick="if (!(confirm('Are you sure you want to delete this transaction?'))) return false">Delete</a> 
				    </td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	</body>
</html>