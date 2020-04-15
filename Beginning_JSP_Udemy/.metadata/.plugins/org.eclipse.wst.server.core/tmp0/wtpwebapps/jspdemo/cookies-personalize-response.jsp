<%@ page import="java.net.URLEncoder" %>

<html>

<head><title>Confirmation</title></head>

<%
	//read form data
	String favLang = request.getParameter("favoriteLanguage");

	//encode cookie data ... handles case of languages with spaces
	favLang = URLEncoder.encode(favLang, "UTF-8");
	
	//create the cookie
	Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);
	
	//Set the life span of the cookie(in seconds)
	theCookie.setMaxAge(60*60*24*365);
	
	//send cookie to browser
	response.addCookie(theCookie);
%>

<body>

	Thanks! We set your favorite language to: ${param.favoriteLanguage}

	<br/><br/>
	
	<a href="cookies-homepage.jsp">Return to homepage.</a>

</body>

</html>