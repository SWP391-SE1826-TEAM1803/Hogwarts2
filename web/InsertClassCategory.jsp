%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.TeacherInfo" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Admin - Hogwarts</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icson">
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

         <script>
        function validateForm() {
            var cateName = document.getElementById('cateName').value.trim();
            var className = document.getElementById('className').value.trim();
            var errorMessages = [];

            // Ki?m tra tr?ng
            if (cateName === "") {
                errorMessages.push("Category Name must not be empty.");
            }
            if (className === "") {
                errorMessages.push("Class Name must not be empty.");
            }

            // Hi?n th? thông báo l?i n?u có
            if (errorMessages.length > 0) {
                var errorMessage = errorMessages.join("<br>");
                document.getElementById('errorMessages').innerHTML = errorMessage;
                return false; // Ng?n ng??i dùng g?i form
            } else {
                // Ki?m tra trùng l?p Category Name và Class Name b?ng Ajax
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        var response = this.responseText;
                        if (response.startsWith("CategoryExists")) {
                            document.getElementById('errorMessages').innerHTML = "Category name already exists. Please choose another name.";
                        } else if (response.startsWith("ClassExists")) {
                            document.getElementById('errorMessages').innerHTML = "Class name already exists. Please choose another name.";
                        } else {
                            // Cho phép g?i form n?u không có l?i
                            document.getElementById('insertForm').submit();
                        }
                    }
                };
                xhttp.open("GET", "CheckDuplicateNamesServlet?cateName=" + cateName + "&className=" + className, true);
                xhttp.send();
                return false; // Ng?n ng??i dùng g?i form cho ??n khi Ajax x? lý xong
            }
        }
    </script>
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
                    <form id="insertForm" action="InsertServlet" method="post" onsubmit="return validateForm()">
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
                        <form action="InsertServlet" method="post" onsubmit="return validateForm()">
                            <input type="hidden" name="action" value="insertClass">
                            <input type="hidden" name="cateID" value="${cateID}">
                            <div class="mb-3">
                                <label for="className" class="form-label">Class Name:</label>
                                <input type="text" class="form-control" id="className" name="className" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Insert Class</button>
                        </form>
                    </c:if>

                    <div id="errorMessages" class="alert alert-danger mt-3"></div>
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
