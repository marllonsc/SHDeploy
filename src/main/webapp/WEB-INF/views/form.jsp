<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HSDeploy</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script type="text/javascript">
	//function alertName() {
	//	alert("Success! Your project has been created successfully.");
	//}
</script>

</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#">HSDeploy</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/">Home</a></li>

				<!--  <li class="nav-item">
                    <a class="nav-link" href="second_page.html">Second Page</a>
                </li>
               
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li> -->

				<li class="nav-item"><a class="btn btn-success ml-2"
					href="${pageContext.request.contextPath}/create">Create Project</a>
				</li>
			</ul>
		</div>
	</nav>

	<header class="bg-primary text-white text-center py-5">
		<h1>System to Deploy Application on Kubernetes</h1>
		<!-- Add any header content here -->
	</header>

	<main class="container mt-4">
		<!-- Your main content goes here -->

		<!-- Project Form -->
		<div class="card">
			<div class="card-body">
				<h2 class="card-title">Create Project</h2>

				<!-- Project Form -->
				<form action="${pageContext.request.contextPath}/create_project"
					method="post">
					<div class="form-group">
						<label for="projectName">Name</label> <input type="text"
							class="form-control" id="projectName" name="name"
							placeholder="Enter project name" required>
					</div>
					<div class="form-group">
						<label for="projectName">Path App</label> <input type="text"
							class="form-control" id="projectPath" name="pathApp"
							placeholder="Enter project path App">
					</div>
					<div class="form-group">
						<label for="projectName">Path Project</label> <input type="text"
							class="form-control" id="appPath" name="pathProject"
							placeholder="Enter project Path project">
					</div>
					<div class="form-group">
						<label for="gitField">Git</label> <input type="text"
							class="form-control" id="gitField" name="git"
							placeholder="Enter Git URL" required>
					</div>

					<div class="form-group">
						<label for="ipPort">Port</label>
						<input type="text" class="form-control" id="ipPort" name="ipPort" placeholder="Enter Port">
					</div>

					<div class="form-group">
						<label for="programmingLanguage">Programming Language</label>
						<select class="form-control" id="programmingLanguage" name="programmingLanguage" required>
							<c:forEach var="language" items="${T(com.marllonsc.entity.ProgrammingLanguage).values()}">
								<option value="${language}">${language}</option>
							</c:forEach>
						</select>
					</div>

					<button type="submit" OnClick="" class="btn btn-primary">Create</button>
				</form>
			</div>
		</div>
		<br />
	</main>

	<footer class="bg-dark text-white text-center py-3">
		<!-- Your footer content goes here -->
		<p>&copy; 2023 marllonsc.com. All rights reserved.</p>
	</footer>

	<!-- Bootstrap JS and Popper.js -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<!-- Your custom JavaScript -->
	<script>
		// Your JavaScript code goes here
		document.addEventListener('DOMContentLoaded', function() {
			// Example: Change background color on click
			document.body.addEventListener('click', function() {
				document.body.style.backgroundColor = 'lightgray';
			});
		});
	</script>

</body>
</html>
