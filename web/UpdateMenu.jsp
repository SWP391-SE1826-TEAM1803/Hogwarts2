<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Menu" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Update Menu - Hogwarts</title>
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
                <h1>Update Menu</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a href="MenuControllerURL?service=listAll">Menu List</a></li>
                        <li class="breadcrumb-item active">Update Food</li>
                    </ol>
                </nav>
            </div>

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <%
                                    Menu menu = (Menu) request.getAttribute("menu");
                                    if (menu != null) {
                                %>
                                <!-- Update Menu Form -->

                                <div class="card-body">
                                    <form action="MenuControllerURL" method="post">
                                        <input type="hidden" name="service" value="update">
                                        <input type="hidden" name="submit" value="submit">
                                        <input type="hidden" name="MenuID" value="<%= menu.getMenuID() %>">
                                        <div class="form-group">
                                            <br>
                                            <label for="Food">Food Name</label>  
                                            <br></br>
                                            <input type="text" class="form-control" id="Food" name="Food" value="<%= menu.getFood() %>" required>
                                        </div>
                                        <br>
                                        <button type="submit" class="btn btn-primary">Update Food</button>
                                    </form>
                                </div>

                                <%
                                    } else {
                                %>
                                <div class="alert alert-danger" role="alert">
                                    Menu item not found.
                                </div>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                    </div>
                </div>
            </section>
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
