<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a rating</title>
</head>
<body>
<form action="ratingupdate" method="post">
		<h1>Write down the ratingId and the new score</h1>
		<p>
			<label for="ratingId">ratingId</label>
			<input id="ratingId" name="ratingId" value="${fn:escapeXml(param.ratingId)}">
		</p>
				<p>
			<label for="newScore">newScore</label>
			<input id="newScore" name="newScore" value="${fn:escapeXml(param.newScore)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="RatingUpdate"><a href="RatingUpdate">Update Ratings</a></div>
	<br/>
        <table border="1">
            <tr>
                <th>ratingId</th>
                <th>score</th>
                <th>userId</th>
                <th>movieName</th>
                <th>Date</th>
            </tr>
            <c:forEach items="${Ratings}" var="Ratings" >
                <tr>
                    <td><c:out value="${Ratings.getRatingId()}" /></td>
                    <td><c:out value="${Ratings.getScore()}" /></td>
                    <td><c:out value="${Ratings.getUser().getUserId()}" /></td>
                    <td><c:out value="${Ratings.getMovie().getTitle()}" /></td>
                    <td><fmt:formatDate value="${Ratings.getTimes()}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
       </table>


</body>
</html>