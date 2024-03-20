<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    //    alert("Success! Your project has been created successfully.");
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
                <li class="nav-item"><a class="btn btn-info ml-2"
                    href="${pageContext.request.contextPath}/registry">Registry Actions</a>
                </li>
            </ul>
        </div>
    </nav>

    <header class="bg-primary text-white text-center py-5">
        <h1>System to Deploy Application to Docker Server</h1>
        <!-- Add any header content here -->
    </header>

    <main class="container mt-4">
        <!-- Your main content goes here -->

        <!-- Project Form -->
        <div class="card">
            <div class="card-body">
                <h2 class="card-title"Registry Actions</h2>

                <h1 class="mb-4">Registry Actions</h1>

                <c:if test="${list.size() == 0}">
                    <br />
                    <h5>There is no Registry Actions!</h5>
                    <br />
                    <br />
                </c:if>

                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Project Name</th>
                            <th>Action Name</th>
                            <th>Date Executed</th>
                            <th>User</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="registryAction" items="${pagedList}">
                            <tr>
                                <td>${registryAction.id}</td>
                                <td>${registryAction.projectId.name}</td>
                                <td>${registryAction.actionName}</td>
                                <td><fmt:formatDate value="${registryAction.dateExecuted}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td>${registryAction.user}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${page eq i ? 'active' : ''}">
                                <a class="page-link" href="?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
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
