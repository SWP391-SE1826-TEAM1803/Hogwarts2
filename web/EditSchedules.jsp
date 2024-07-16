<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Edit Schedule - Hogwarts</title>
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
            <h1>Edit Schedule</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="SchedulesControllerURL?service=viewSchedules">Schedules</a></li>
                    <li class="breadcrumb-item active">Edit Choose a Curriculum Again</li>
                </ol>
            </nav>
        </div>

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Edit Schedule</h5>
                            <form action="SchedulesControllerURL" method="post" class="w-100">
                                <input type="hidden" name="service" value="updateSchedule">
                                <input type="hidden" name="schedulesID" value="${schedule.schedulesID}">
                                <div class="mb-3">
                                    <label for="date" class="form-label">Date:</label>
                                    <input type="date" id="date" name="date" class="form-control" value="${schedule.date}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label for="curDateID" class="form-label">Curriculum Date:</label>
                                    <select class="form-control" id="curDateID" name="curDateID" required>
                                        <c:forEach var="curriculumDate" items="${curriculumDates}">
                                            <option value="${curriculumDate.curDateID}" ${curriculumDate.curDateID == schedule.curDateID ? 'selected' : ''}>
                                                ${curriculumDate.dateNumber} - ${curriculumDate.curName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Update Schedule</button>
                            </form>
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
