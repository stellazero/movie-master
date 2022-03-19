<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Rating</title>
</head>
<body>
	<form action="ratingcreate" method="post">
		<h1>Write down your Id and the movie you want to rate</h1>
		<p>
			<label for="userId">userId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
				<p>
			<label for="Title">Title</label>
			<input id="Title" name="Title" value="${fn:escapeXml(param.Title)}">
		</p>
		<p>
			<label for="score">score</label>
			<input id="score" name="score" value="${fn:escapeXml(param.score)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

</body>
</html>