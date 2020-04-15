<%@ page import="java.util.*" %>
<html>

<body>

<!-- Step 1: Create HTML form -->
<form action="todo-demo.jsp">
	Add new Item: <input type="text" name="theItem" />
	
	<input type="submit" value="Submit" />
</form>

<!-- Step 2: Add new item to "To Do" list -->
<%

	//get the TO DO items from the session
	List<String> items = (List<String>) session.getAttribute("myToDoList");	
	
	//if TO DO items don't exist, then create new one
	if(items == null)
	{ 
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}
	
	
	//See if there is form data to add
	String theItem = request.getParameter("theItem");
	
	boolean isItemNotEmpty =  (theItem != null) && (theItem.trim().length() > 0);
	boolean isItemNotDuplicate = (theItem != null) && (!items.contains(theItem.trim()));
	if (isItemNotEmpty && isItemNotDuplicate)
	{
		items.add(theItem.trim());
		response.sendRedirect("todo-demo.jsp");
	}

%>

<!-- Step 3: Display all "To Do" items from session -->
	<hr>
	<b>To Do List Items:</b> <br/>
	
	<ol>	
	<%
	
		for(String temp : items)
		{
			out.println("<li>" + temp + "</li>");
		}
	
	%>	
	</ol>
</body>


</html>