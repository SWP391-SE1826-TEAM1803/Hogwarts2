<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.TeacherInfo" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Admin - Hogwarts</title>
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
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
    <%@include file="HeaderAdmin.jsp"%>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Teacher List</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Teachers</li>
                </ol>
            </nav>
        </div>

        <div class="col-12">
            <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <!-- Buttons for Active and Inactive -->
                        <button class="btn btn-primary filter-btn" data-status="Active">Active</button>
                        <button class="btn btn-secondary filter-btn" data-status="Inactive">Inactive</button>
                        <a href="AddUserAndTeacher.jsp" class="btn btn-success">Add New Teacher</a>
                    </div>
                    <!-- Table -->
                    <table class="table table-borderless datatable mt-3">
                        <thead>
                            <tr>
                                <th scope="col">Teacher Name</th>
                                <th scope="col">Class Name</th>
                                <th scope="col">Degree</th>
                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% Vector<TeacherInfo> vector = (Vector<TeacherInfo>) request.getAttribute("data");
                                if (vector != null) {
                                    for (TeacherInfo teacherInfo : vector) {
                                        String nameClass = teacherInfo.getClassName() != null ? "Active" : "Inactive";
                                        %>
                                <tr>
                                    <td><%= teacherInfo.getTeacherName() %></td>
                                    <td><%= teacherInfo.getClassName() %></td>
                                    <td><%= teacherInfo.getDegree() %></td>
                                    <td><%= nameClass %></td>
                                   
                                </tr>
                            <% } } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

    <%@include file="Footer.jsp"%>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

    <!-- Script for filtering -->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const filterButtons = document.querySelectorAll('.filter-btn');

            filterButtons.forEach(btn => {
                btn.addEventListener('click', function () {
                    const status = this.getAttribute('data-status');
                    filterTeachers(status);
                });
            });

            function filterTeachers(status) {
                const tableRows = document.querySelectorAll('.datatable tbody tr');

                tableRows.forEach(row => {
                    const statusCell = row.querySelector('td:nth-child(4)');
                    const currentStatus = statusCell.textContent.trim();

                    if (status === 'All' || currentStatus === status) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }
        });
    </script>
</body>

</html>
