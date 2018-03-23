<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>board</h2>
		<table class="table table-boardered">
			<thead>
				<tr>
					<th>bno</th>
					<th>contents</th>
					<th>userName</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="test" items="${testList}">
					<tr>
						<td>${test.bno }</td>
						<td><a href="#">${test.contents}</a></td>
						<td>${test.userName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>