<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.ClassCategory"%>
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
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">

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
        <div class="container mt-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Class Categories</h5>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Category ID</th>
                                <th scope="col">Category Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                Vector<ClassCategory> categories = (Vector<ClassCategory>) request.getAttribute("categories");
                                if (categories != null) {
                                    for (ClassCategory category : categories) {
                            %>
                            <tr>
                                <td><%= category.getCateID() %></td>
                                <td><%= category.getCateName() %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="2">No categories found.</td>
                            </tr>
                            <% 
                                } 
                            %>
                        </tbody>
                    </table>
                        <div class="mb-3">
                                    <a href="InsertClassCategory.jsp" class="btn btn-primary">Add New Class Category</a>
                                </div>
                </div>
            </div>
        </div>
    </main>
    <%@include file="Footer.jsp"%>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/chart.js/chart.umd.js"></script>
    <script src="assets/vendor/echarts/echarts.min.js"></script>
    <script src="assets/vendor/quill/quill.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
</body>
</html>
