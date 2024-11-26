<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Participant</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>Update Participant</h1>
	<form action="participant" method="post">
		<input type="hidden" name="_method" value="put"> <input
			type="hidden" name="id" value="${participant.id}"> <label
			for="name">Name:</label> <input type="text" id="name" name="name"
			value="${participant.name}" required> <label for="email">Email:</label>
		<input type="email" id="email" name="email"
			value="${participant.email}" required> <label for="batchId">Batch
			ID:</label> <input type="number" id="batchId" name="batchId"
			value="${participant.batchId}" required>

		<button type="submit">Update Participant</button>
	</form>
</body>
</html>
