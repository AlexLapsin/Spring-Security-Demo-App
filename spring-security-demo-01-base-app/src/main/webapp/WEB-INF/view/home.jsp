<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>    


<html>
<head>
	<title>Company Home Page</title>
</head>

<body>
	<h2>Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to company home page!
	</p>
	
	<hr>
	
	<!-- Display user name and role -->		
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>	
	
	<hr>
	
	<!-- Add a link to point to /leaders ... this is for the managers -->
	<p>
	<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
	(Only for Managers)
	</p>
	
	<hr>
	
	<!-- Add a log out button -->
	<form:form action="${pageContext.request.contextPath}/logout"
				method="POST">
				
		<input type="submit" value="Logout"/>
	</form:form>
	
</body>

</html>