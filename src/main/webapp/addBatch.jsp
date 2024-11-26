<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles.css">
<title>Add Batch</title>
</head>
<body>
	<div class="container">
		<h2>Add Batch</h2>
		<form action="batch" method="post">
			<div class="form-group">
				<label for="name">Batch Name:</label> <input type="text" id="name"
					name="name" required>
			</div>
			<div class="form-group">
				<label for="timing">Batch Timing:</label> <input type="text"
					id="timing" name="timing" required>
			</div>
			<button type="submit">Add Batch</button>
		</form>
	</div>
</body>
</html>
