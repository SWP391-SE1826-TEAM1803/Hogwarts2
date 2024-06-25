<%-- 
    Document   : Home
    Created on : May 22, 2024, 1:28:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.DAOTeacher, model.DAOStudentSchoolYearClass, model.DAOUser, model.DAOClass, model.DAOMenu, model.DAOCurriculum" %>

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
  <style>
      .card-icon {
  width: 50px;
  height: 50px;
  background-color: #f0f0f0; /* or any preferred background color */
  font-size: 24px;
  color: #000; /* or any preferred icon color */
}

  </style>

  
</head>

<body>
    <%@include file="HeaderAdmin.jsp"%>
  <main id="main" class="main">
        <div class="pagetitle">
            <h1>Home</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a>Home</a></li>
                </ol>
            </nav>

            <div class="container">
                <div class="row">
                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Users <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-person"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOUser daoU = new DAOUser();
                                            int userCount = daoU.getUserCount();
                                            out.print(userCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Classes <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-door-open"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOClass daoC = new DAOClass();
                                            int classCount = daoC.getClassCount();
                                            out.print(classCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Teacher Card -->
                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Teachers <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-pen"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOTeacher dao = new DAOTeacher();
                                            int teacherCount = dao.getTeacherCount();
                                            out.print(teacherCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Student Card -->
                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Students <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-backpack"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOStudentSchoolYearClass daoSt = new DAOStudentSchoolYearClass();
                                            int studentCount = daoSt.getStudentCount();
                                            out.print(studentCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Curriculums <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-book"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOCurriculum daoCur = new DAOCurriculum();
                                            int curCount = daoCur.getCurCount();
                                            out.print(curCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xxl-4 col-xl-6 mb-3">
                        <div class="card info-card customers-card">
                            <div class="card-body">
                                <h5 class="card-title">Menus <span>| This Year</span></h5>
                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-egg"></i>
                                    </div>
                                    <div class="ms-3">
                                        <h6 class="mb-0"><%
                                            DAOMenu daoM = new DAOMenu();
                                            int menuCount = daoM.getMenuCount();
                                            out.print(menuCount);
                                        %></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!-- End Row -->
            </div><!-- End Container -->
        </div><!-- End Page Title -->
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