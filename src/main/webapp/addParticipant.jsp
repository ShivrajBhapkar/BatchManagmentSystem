<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Participant</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Add New Participant</h1>
        <form action="participant" method="post">
            <div class="form-group">
                <label for="name">Participant Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="batch">Select Batch:</label>
                <select id="batch" name="batch_id" required>
                    <c:forEach var="batch" items="${batches}">
                        <option value="${batch.id}">${batch.name} - ${batch.timing}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit">Add Participant</button>
        </form>
        <a href="participant">Back to Participants</a>
    </div>
</body>
</html>
