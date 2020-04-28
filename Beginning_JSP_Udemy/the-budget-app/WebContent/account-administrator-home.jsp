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
			<h2>Account Administrator Home</h2>
		</div>
	</div>
	<h4>Logged in to account: AccountName</h4>
	<h4>Logged in as        : UserName</h4>
	<h4>Total Budget        : $0.00</h4>
	<h4>Budget Remaining    : $0.00</h4>
	<div id="container">
		<div id="content">
		<!-- Button to add member to account -->
		<input type="button" value="Add Member" 
		onclick="window.location.href='add-member-form.jsp'; return false;"
		class="add-member-button" />
		<input type="button" value="Update Account" 
		onclick="window.location.href='update-account-form.jsp'; return false;"
		class="update-account-button" />
		<input type="button" value="Add Transaction" 
		onclick="window.location.href='add-transaction-form.jsp'; return false;"
		class="add-transaction-button" />
		<table>
		<h3>Account Member List</h3>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th> 
					<th>Account Id</th> 
					<th>Action</th>
				</tr>
				<c:forEach var="tempMember" items="${MEMBER_LIST}">
					<!-- Set up link to update each member -->
					<c:url var="tempLink" value="MemberControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="memberId" value="${tempMember.id}" />
					</c:url>
					<!-- Set up Link to delete each member -->
					<c:url var="deleteLink" value="MemberControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="memberId" value="${tempMember.id}" />
					</c:url>
					<tr>
						<td> ${tempMember.name} </td>
						<td> ${tempMember.userName} </td>
						<td> ${tempMember.email} </td>
						<td> ${tempMember.account_id} </td>						
						<td> <a href="${tempLink}">Update</a> | 
							 <a href="${deleteLink}"
							    onclick="if (!(confirm('Are you sure you want to delete this Member?'))) return false">
							    Delete</a> </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div id="container">
		<div id="content">
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