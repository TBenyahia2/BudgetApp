<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<title>App Member List</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Member Table</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		<!-- Button to add member to account -->
		<input type="button" value="Add Member" 
		onclick="window.location.href='add-member-form.jsp'; return false;"
		class="add-member-button" />
		<table>
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

</body>
</html>