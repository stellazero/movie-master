<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Movie By Title</title>
</head>
<body>
	<form action="findmoviebydirector" method="post">
		<p>
			<label for="movieDirector">Please enter the director name you are looking for</label>
			<br/>
			<input id="movieDirector" name="movieDirector" value="${fn:escapeXml(param.movieDirector)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<h1>Matching Movies</h1>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Year</th>
                <th>Duration</th>
                <th>Languages</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${movies}" var="movies" >
                <tr>
                    <td><c:out value="${movies.getTitle()}" /></td>
                    <td><c:out value="${movies.getYear()}"/></td>
                    <td><c:out value="${movies.getDuration()}" /></td>
                    <td><c:out value="${movies.getLanguages()}" /></td>
                    <td><c:out value="${movies.getDescription()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>