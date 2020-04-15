<%@ page import="java.net.URLDecoder" %>
<html>

<body>
<h3>Training Portal</h3>

<!-- read the favorite programming language cookie -->
<%
	//the default...no cookies
	String favLang = null;
	
	//get cookies from browser request
	Cookie[] theCookies = request.getCookies();
	
	//find our favorite programming language cookie
	if (theCookies != null)
	{
		for (Cookie tempCookie : theCookies)
		{
			if ("myApp.favoriteLanguage".equals(tempCookie.getName()))
			{
				favLang = URLDecoder.decode(tempCookie.getValue());
				break;
			}
		}
	}
	
%>

<!-- Show personalized page based on favLang variable -->
<h4>New Books for <%= favLang %></h4>
<ul>
	<li>blah blah blah</li>
	<li>blah blah blah</li>
</ul>
<h4>News for <%= favLang %></h4>
<ul>
	<li>blah blah blah</li>
	<li>blah blah blah</li>
</ul>
<h4>Jobs for <%= favLang %></h4>
<ul>
	<li>blah blah blah</li>
	<li>blah blah blah</li>
</ul>


<hr>
<a href="cookies-personalize-form.html">Personalize this page</a>

</body>

</html>