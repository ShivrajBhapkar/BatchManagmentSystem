<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Batches</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<header>
		<h1>Batch Management</h1>
	</header>

	<h2>List of Batches</h2>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Timing</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="batch" items="${batches}">
				<tr>
					<td>${batch.id}</td>
					<td>${batch.name}</td>
					<td>${batch.timing}</td>
					<td><a href="batch?id=${batch.id}">Edit</a>
						<form action="batch" method="post" style="display: inline;">
							<input type="hidden" name="_method" value="delete"> <input
								type="hidden" name="id" value="${batch.id}">
							<button type="submit">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<footer>
		<p>&copy; 2024 Batch Management System</p>
	</footer>
</body>
</html>
