<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Rating by UserId</title>
</head>
<body>
	<form action="findratingbyuserid" method="post">
		<h1>Write down your UserId</h1>
		<p>
			<label for="userId">userId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<h1>Matching Ratings</h1>
        <table border="1">
            <tr>
                <th>RatingId</th>
                <th>MovieTitle</th>
                <th>Score</th>
            </tr>
            <c:forEach items="${ratings}" var="ratings" >
                <tr>
                    <td><c:out value="${ratings.getRatingId()}" /></td>
                    <td><c:out value="${ratings.getMovie().getTitle()}"/></td>
                    <td><c:out value="${ratings.getScore()}" /></td>
                </tr>
            </c:forEach>
       </table>

</body>
</html>