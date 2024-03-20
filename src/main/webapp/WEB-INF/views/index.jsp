<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HSDeploy</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">HSDeploy</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/">Home <span
						class="sr-only">(current)</span></a></li>

				<!--  <li class="nav-item">
                    <a class="nav-link" href="second_page.html">Second Page</a>
                </li>
               
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/" style="color: #080404"s;>
						Mysql IP: ${Mysql}</a></li>-->

				<li class="nav-item"><a class="btn btn-success ml-2"
					href="${pageContext.request.contextPath}/create">Create Project</a>
				</li>
				<li class="nav-item"><a class="btn btn-alert ml-2"
					href="${pageContext.request.contextPath}/registry">Registry Actions</a>
				</li>
			</ul>
		</div>
	</nav>

	<header class="bg-primary text-white text-center py-5">
		<h1>System to Deploy Application to Docker Server</h1>
		<!-- Add any header content here -->
	</header>

	<c:if test="${message != '' && message != null}">
		<div class="container mt-3">
			<div
				class="alert alert-${status == 1 ? 'success' : 'danger'} alert-dismissible fade show"
				role="alert">
				<strong>Message: </strong> ${message}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</c:if>


	<main class="container mt-4">

		<!-- Your main content goes here -->
		<h2>List of Applications</h2>

		<c:if test="${list.size() == 0}">
			<br />
			<h5>there is no Application create!</h5>
			<br />
			<br />
		</c:if>

		<c:forEach var="project" items="${list}">


			<!-- Project Actions -->
			<div class="card">
				<div class="card-body">
					<h2 class="card-title">
						Application - <b>${project.name}</b> Actions
					</h2>

					<!-- Buttons -->
					<div class="btn-group" role="group" aria-label="Project Actions">
						<c:if test="${project.init == 0}">
							<a class="btn btn-primary"
								href="${pageContext.request.contextPath}/init_project/${project.id}">Init
								Project</a>
						</c:if>
						<c:if test="${project.deploy == 0}">
							<a class="btn btn-success"
								href="${pageContext.request.contextPath}/deploy_project/${project.id}">Deploy
								Project</a>
						</c:if>
						<c:if test="${project.deploy != 0}">
							<a class="btn btn-warning"
								href="${pageContext.request.contextPath}/del_deploy_project/${project.id}">UnDeploy
								Project</a>
						</c:if>

						<c:if test="${project.deploy == 0}">
							<a class="btn btn-danger"
								href="${pageContext.request.contextPath}/del_project/${project.id}">Delete
								Project</a>
						</c:if>

					</div>

					<!-- Information List -->
					<ul class="list-group mt-3">
						<li class="list-group-item"><b>Name: </b>${project.name}</li>
						<li class="list-group-item"><b>Path Project: </b>${project.pathProject}</li>
						<li class="list-group-item"><b>Path App: </b>${project.pathApp}</li>
						<li class="list-group-item"><b>git: </b>${project.git}</li>
						<li class="list-group-item"><b>Service: </b>${project.ipPort}</li>
						<li class="list-group-item"><b>Programming Language: </b>${project.programmingLanguage}</li>
						<!-- li class="list-group-item"><b>Service Created: </b>${project.service == 0 ? false : true}</li -->
						<li class="list-group-item"><b>Project Init: </b>${project.init == 0 ? false : true}</li>
						<li class="list-group-item"><b>Project Deployed: </b>${project.deploy == 0 ? false : true}</li>
						<!-- Add more list items as needed -->
					</ul>
				</div>
			</div>
			<br />


		</c:forEach>


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