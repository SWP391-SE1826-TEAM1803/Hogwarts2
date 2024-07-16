<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Admin - Hogwarts</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
    <%@include file="HeaderAdmin.jsp"%>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Insert Class and Category</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Insert Class and Category</li>
                </ol>
            </nav>
        </div>

        <div class="col-12">
            <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title">Add New Category</h5>
                    </div>
                    <form id="categoryForm" action="InsertServlet" method="post">
                        <input type="hidden" name="action" value="insertCategory">
                        <div class="mb-3">
                            <label for="cateName" class="form-label">Category Name:</label>
                            <input type="text" class="form-control" id="cateName" name="cateName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Insert Category</button>
                    </form>

                    <c:if test="${not empty cateID}">
                        <div class="d-flex justify-content-between align-items-center mb-3 mt-5">
                            <h5 class="card-title">Add New Class</h5>
                        </div>
                        <form id="classForm" action="InsertServlet" method="post">
                            <input type="hidden" name="action" value="insertClass">
                            <input type="hidden" name="cateID" value="${cateID}">
                            <div class="mb-3">
                                <label for="className" class="form-label">Class Name:</label>
                                <input type="text" class="form-control" id="className" name="className" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Insert Class</button>
                        </form>
                    </c:if>

                    <c:if test="${not empty message}">
                        <div class="alert alert-info mt-3">
                            ${message}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </main>

    <%@include file="Footer.jsp"%>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
    <script>
        document.getElementById('categoryForm').addEventListener('submit', function(event) {
            event.preventDefault();
            let cateName = document.getElementById('cateName').value;
            let xhr = new XMLHttpRequest();
            xhr.open('POST', 'InsertServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText == 'true') {
                        alert('Category name already exists. Please choose a different name.');
                    } else {
                        document.getElementById('categoryForm').submit();
                    }
                }
            };
            xhr.send('action=checkCategory&cateName=' + cateName);
        });

        document.getElementById('classForm').addEventListener('submit', function(event) {
            event.preventDefault();
            let className = document.getElementById('className').value;
            let xhr = new XMLHttpRequest();
            xhr.open('POST', 'InsertServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText == 'true') {
                        alert('Class name already exists. Please choose a different name.');
                    } else {
                        document.getElementById('classForm').submit();
                    }
                }
            };
            xhr.send('action=checkClass&className=' + className);
        });
    </script>
</body>
</html>
