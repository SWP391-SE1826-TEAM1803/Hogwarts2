<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.Class" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Class List - Hogwarts</title>
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
                <h1>Class List</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                        <li class="breadcrumb-item active">Class List</li>
                    </ol>
                </nav>
            </div>

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <!-- Add New Class Form -->
                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <form action="ClassControllerURL" method="post" class="w-100">
                                        <input type="hidden" name="service" value="addClass">
                                        <div class="form-group">
                                            <br>
                                            <label for="className">Class Name</label>
                                            <input type="text" class="form-control" id="className" name="className" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="cateID"> Choose Category Class</label>
                                            <select class="form-control" id="cateID" name="cateID" required>
                                                <option value="1">Age 3</option>
                                                <option value="2">Age 4</option>
                                                <option value="3">Age 5</option>
                                            </select>
                                        </div>
                                        <br>
                                        <button type="submit" class="btn btn-primary">Add Class</button>
                                    </form>
                                </div>

                                <!-- Class List Table -->
                                <table class="table table-borderless datatable mt-3">
                                    <thead>
                                        <tr>
<!--                                            <th scope="col">ID</th>-->
                                            <th scope="col">Class Name</th>
                                            <th scope="col">Category Class</th>
                                            <th scope="col">Detail</th>
                                            <th scope="col" style="text-align: center;">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            Vector<Class> classList = (Vector<Class>) request.getAttribute("classList");
                                            if (classList != null) {
                                                for (Class cls : classList) {
                                        %>
                                        <tr>
<!--                                            <td><//%= cls.getClassID() %></td>-->
                                            <td><%= cls.getClassName() %></td>
                                            <td><%= cls.getCateName() %></td>
                                            <td>
                                                <a class="btn btn-outline-info btn-sm" href="ClassControllerURL?service=detailClass&classID=<%= cls.getClassID() %>">Detail</a>
                                            </td>
                                            <td style="text-align: center;">
                                                <a class="btn btn-outline-warning btn-sm" href="ClassControllerURL?service=updateClass&classID=<%= cls.getClassID() %>">Update</a>
                                                <a class="btn btn-outline-danger btn-sm" href="ClassControllerURL?service=deleteClass&classID=<%= cls.getClassID() %>" onclick="return confirm('Are you sure you want to delete this class?')">Delete</a>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            } else {
                                        %>
                                        <tr>
                                            <td colspan="5">No classes found.</td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
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
