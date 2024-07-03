<%-- 
    Document   : Home
    Created on : May 22, 2024, 1:28:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.SchoolYearClass, entity.TeacherSchoolYearClass, entity.SchoolYear, entity.Class, model.DAOSchoolYear, model.DAOClass, model.DAOTeacherSchoolYearClass, model.DAOTeacher"%>

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
            <h1>School Year Detail</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="SchoolYearControllerURL?service=listAll">School Years</a></li>
                    <li class="breadcrumb-item active">Detail</li>
                </ol>
            </nav>
        </div>
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">School Year Information</h5>
                    <table class="table table-borderless datatable">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Class ID</th>
                                <th scope="col">Class Name</th>
                                <th scope="col">Curriculum ID</th>
                                <th scope="col">Teacher</th>
                                <th scope="col" style="text-align: center;">Detail</th>
                                <th scope="col" style="text-align: center;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%  
                                DAOClass dao = new DAOClass();
                                DAOTeacherSchoolYearClass daoTSC = new DAOTeacherSchoolYearClass();
                                DAOTeacher daoT = new DAOTeacher();
                                Vector<SchoolYearClass> syClasses = (Vector<SchoolYearClass>) request.getAttribute("data");
                                for (SchoolYearClass syClass : syClasses) {
                                    Class classObj = dao.getClassByID(syClass.getClassID());
                                    TeacherSchoolYearClass teachersObj = daoTSC.getTeacherSchoolYearClassesBySyC_ID(syClass.getSyC_ID());
                            %>
                            <tr>
                                <td><%= syClass.getSyC_ID() %></td>
                                <td><%= syClass.getClassID() %></td>
                                <td><%= classObj != null ? classObj.getClassName() : "N/A" %></td>
                                <td><%= syClass.getCurID() %></td>
                                <td><%= teachersObj != null ? teachersObj.getTeacherID() : "N/A" %></td>
                                <td style="text-align: center;">
                                    <a class="btn btn-outline-info btn-icon-text" href="SchoolYearClassDetail.jsp?SyC_ID=<%=syClass.getSyC_ID()%>">
                                        <i class="mdi mdi-information"></i> Detail
                                    </a>                                    
                                </td>
                                <td style="text-align: center;">
                                    <a class="btn btn-outline-warning btn-icon-text" href="SchoolYearClassControllerURL?service=updateSchoolYearClass&SyC_ID=<%=syClass.getSyC_ID()%>">
                                        <i class="mdi mdi-refresh"></i> Update
                                    </a>
                                    <a class="btn btn-outline-danger btn-icon-text" href="SchoolYearClassControllerURL?service=deleteSchoolYearClass&SyC_ID=<%=syClass.getSyC_ID()%>">
                                        <i class="mdi mdi-delete-forever"></i> Delete
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
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