<!DOCTYPE html>
<html>
<head>
	<title>Add Transaction</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Add Transaction</h2>
		</div>
	</div>
	<div id="container">
		<h3>Add Transaction</h3>
		<form action="TransactionControllerServlet" method="POST">
			<input type="hidden" name="command" value="ADD"/>
			<table>
				<tbody>
					<tr>
						<td><label>Account ID:</label></td>
						<td><input type="text" name="account_id" /></td>							
					</tr>
					<tr>
						<td><label>Member ID:</label></td>
						<td><input type="text" name="member_id" /></td>							
					</tr>
					<tr>
						<td><label>Expense:</label></td>
						<td><input type="text" name="name" /></td>							
					</tr>
					<tr>
						<td><label>Amount:</label></td>
						<td><input type="text" name="amount" /></td>							
					</tr>
					<tr>
						<td><label>Date:</label></td>
						<td><input type="text" name="date" /></td>							
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" name="Add" class="save" /></td>							
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear: both;">
		<p>
			<a href="TransactionControllerServlet">Back to List</a>
		</p>
		</div>
	</div>
</body>
</html>