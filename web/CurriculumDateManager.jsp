<%@ page import="java.util.Vector" %>
<%@ page import="entity.CurriculumDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Curriculum Date Manager - Hogwarts</title>
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
    <%@ include file="HeaderAdmin.jsp" %>

    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Curriculum Date Manager</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Curriculum Date Manager</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Add New Curriculum Date</h5>
                            <!-- Add New Curriculum Date Form -->
                            <form action="CurriculumDateControllerURL" method="post">
                                <input type="hidden" name="service" value="addCurriculumDate">
                                <div class="mb-3">
                                    <label for="DateNumber" class="form-label">Date Number</label>
                                    <input type="text" class="form-control" id="DateNumber" name="DateNumber" required>
                                </div>
                                <div class="mb-3">
                                    <label for="CurID" class="form-label">Curriculum ID</label>
                                    <input type="number" class="form-control" id="CurID" name="CurID" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Curriculum Date</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Curriculum Date List</h5>
                            <!-- Curriculum Date List Table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Date ID</th>
                                        <th>Date Number</th>
                                        <th>Curriculum ID</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% Vector <CurriculumDate> curDates = (Vector<CurriculumDate)request.getAttribute("curriculumDate") %>
                                    for(CurriculumDate curDate : curDates){
                                        
                                    <tr>
                                            <td>${curriculumDate.curDateID}</td>
                                            <td>${curriculumDate.dateNumber}</td>
                                            <td>${curriculumDate.curID}</td>
                                            <td>
                                                <a href="CurDateActControllerURL" class="btn btn-primary btn-sm">Details</a>
                                                <a href="CurriculumDateControllerURL?service=delete&CurDateID=${curriculumDate.curDateID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                            </td>
                                        </]tr>
                                        <%%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <%@ include file="Footer.jsp" %>

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
