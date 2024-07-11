<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.StudentSchoolYearClass" %>
<%@ page import="java.util.Vector" %>
<%
    Vector<StudentSchoolYearClass> students = (Vector<StudentSchoolYearClass>) request.getAttribute("data");
    if (students != null && !students.isEmpty()) {    
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Home - Hogwarts</title>
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

        <%@include file="HeaderTeacher.jsp"%>

        <main id="main" class="main">
            <div class="pagetitle">
                <h1>Student Class List</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="StudentControllerURL?service=listTeacherKid">Home</a></li>
                                                <li class="breadcrumb-item active"><%= students.get(0).getSchoolYearClass().getSchoolYear().getSyName() %></li>

                        <li class="breadcrumb-item active"><%= students.get(0).getSchoolYearClass().getClassObj().getClassName() %></li>
                    </ol>
                </nav>
            </div>

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <a href="AddFeedback.jsp" class="btn btn-success">Feedback All Student</a>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Full Name</th>
                                                <th scope="col">Date of Birth</th>
                                                <th scope="col">Gender</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Phone </th>
                                                <th scope="col">Parent Name</th>
                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            
                                                    for (StudentSchoolYearClass studentClass : students) {
                                            %>
                                            <tr>
                                                <td><%= studentClass.getStudent().getFullName() %></td>
                                                <td><%= studentClass.getStudent().getDoB() %></td>
                                                <td><%= studentClass.getStudent().getGender() %></td>
                                                <td><%= studentClass.getStudent().getAddress() %></td>
                                                <td><%= studentClass.getStudent().getParent().getPhone() %></td>
                                                <td><%= studentClass.getStudent().getParent().getFullName() %></td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <%
                                    if (students != null) {
                                        out.println("Number of students: " + students.size());
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
