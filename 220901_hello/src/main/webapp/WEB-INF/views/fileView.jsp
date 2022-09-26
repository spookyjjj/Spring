<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ not empty list }">
		<ul>
		<c:forEach var="item" items="${ list }">
			<c:url value="/file/down?filename=${ item }" var="link"/>
			<li><a href="${ link }">${ item }</a></li>
		</c:forEach>
		</ul>
	</c:if>
</body>
</html>