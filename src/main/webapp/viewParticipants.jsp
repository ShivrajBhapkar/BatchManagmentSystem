<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Participants</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Participants</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Batch ID</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="participant" items="${participants}">
				<tr>
					<td>${participant.id}</td>
					<td>${participant.name}</td>
					<td>${participant.email}</td>
					<td>${participant.batchId}</td>
					<td><a href="participant?id=${participant.id}">Update</a>
						<form action="participant" method="post" style="display: inline;">
							<input type="hidden" name="_method" value="delete"> <input
								type="hidden" name="id" value="${participant.id}">
							<button type="submit">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
