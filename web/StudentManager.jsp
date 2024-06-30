<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.StudentSchoolYearClass" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Student Manager - Hogwarts</title>
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
            <h1>Student Manager</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Students</li>
                </ol>
            </nav>
        </div>

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">

                            <div class="d-flex justify-content-between align-items-center">
                                <form class="form-inline" action="StudentControllerURL" method="get">
                                    <div class="input-group">
                                        <select class="form-control mr-2" name="year">
                                            <option value="">All</option>
                                            <option value="2023-2024" <%= "2023-2024".equals(request.getParameter("year")) ? "selected" : "" %>>2023-2024</option>
                                            <option value="2022-2023" <%= "2022-2023".equals(request.getParameter("year")) ? "selected" : "" %>>2022-2023</option>
                                            <option value="2021-2022" <%= "2021-2022".equals(request.getParameter("year")) ? "selected" : "" %>>2021-2022</option>
                                        </select>
                                        <button type="submit" class="btn btn-primary">Year</button>
                                    </div>
                                </form>
                                <a href="StudentControllerURL?service=showAddStudent" class="btn btn-success">Add New Student</a>
                            </div>

                            <table class="table table-borderless datatable mt-3">
                                <thead>
                                    <tr>
                                        <th scope="col">Full Name</th>
                                        <th scope="col">Date of Birth</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Class Name</th>
                                        <th>School Year</th>
                                        <th scope="col">Parent Name</th>
                                        <th scope="col" style="text-align: center;">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        Vector<StudentSchoolYearClass> students = (Vector<StudentSchoolYearClass>) request.getAttribute("data");
                                        if (students != null) {
                                            for (StudentSchoolYearClass studentClass : students) {
                                    %>
                                    <tr>
                                        <td><%= studentClass.getStudent().getFullName() %></td>
                                        <td><%= studentClass.getStudent().getDoB() %></td>
                                        <td><%= studentClass.getStudent().getGender() %></td>
                                        <td><%= studentClass.getStudent().getAddress() %></td>
                                        <td><%= studentClass.getSchoolYearClass().getClassObj().getClassName() %></td>
                                        <td><%= studentClass.getSchoolYearClass().getSchoolYear().getSyName() %></td>
                                        <td><%= studentClass.getStudent().getParent().getFullName() %></td>
                                        <td style="text-align: center;">
                                            <a class="btn btn-outline-warning btn-sm" href="StudentControllerURL?service=showUpdateStudent&studentID=<%= studentClass.getStudent().getStudentID() %>">Update</a>
                                            <a class="btn btn-outline-danger btn-sm" href="StudentControllerURL?service=deleteStudent&studentID=<%= studentClass.getStudent().getStudentID() %>" onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        } else {
                                    %>
                                    <tr>
                                        <td colspan="9">No students found.</td>
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
