<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.StudentSchoolYearClass, entity.Student1, entity.SchoolYear"%>
<%@page import="model.DAOStudent1, model.DAOSchoolYear"%>


<% String cName = (String) request.getAttribute("cName");
String syID = (String) request.getAttribute("syID");
DAOStudent1 daoS = new DAOStudent1();
        DAOSchoolYear daoSY = new DAOSchoolYear();

             Vector<SchoolYear> sy = daoSY.getAllSchoolYears("SELECT * FROM SchoolYear Where SyID = '" + syID + "'");
             String syname = sy.get(0).getSyName();

%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title><%=cName%> - Hogwarts</title>
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
                <h1>Class Detail</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a href="SchoolYearControllerURL?service=listAll">School Years</a></li>
                        <li class="breadcrumb-item"><a href="SchoolYearClassControllerURL?service=searchBySyID&SyID=<%= syID %>"><%=syname%></a></li>
                        <li class="breadcrumb-item active"><%=cName%></li>
                    </ol>
                </nav>
            </div>

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">


                                <div class="d-flex justify-content-between align-items-center">

                                    <a href="" class="btn btn-success">Add Students</a>


                                </div>

                                <table class="table table-borderless datatable mt-3">
                                    <thead>
                                        <tr>
                                            <th scope="col">Full Name</th>
                                            <th scope="col">Date of Birth</th>
                                            <th scope="col">Gender</th>
                                            <th scope="col">Address</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                          Vector<StudentSchoolYearClass> students = (Vector<StudentSchoolYearClass>) request.getAttribute("data");
                                          if (students != null) {
                                            for (StudentSchoolYearClass student : students) {
                                            Vector <Student1> stu = daoS.getAllStudents("select * from Student where StudentID = '"+student.getStudentID()+"'");
                                            
                                        %>
                                        <tr>
                                            <td><%= stu.get(0).getFullName() %></td>
                                            <td><%= stu.get(0).getDoB() %></td>
                                            <td><%= stu.get(0).getGender() %></td>
                                            <td><%= stu.get(0).getAddress() %></td>

                                        </tr>
                                        <%
                                            }
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
