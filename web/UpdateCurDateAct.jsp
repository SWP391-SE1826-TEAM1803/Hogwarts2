<%@ page import="java.util.Vector" %>
<%@ page import="entity.Curriculum" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Update Curriculum Date Activity - Hogwarts</title>
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
            <h1>Update Curriculum Date Activity</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="CurriculumDateManager.jsp">Curriculum Date Manager</a></li>
                    <li class="breadcrumb-item active">Update Curriculum Date Activity</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Update Curriculum Date Activity</h5>
                            <!-- Update Curriculum Date Activity Form -->
                            <form action="CurDateActControllerURL" method="post">
                                <input type="hidden" name="service" value="update">
                                <input type="hidden" name="CdtID" value="${curDateAct.cdtID}">
                                <div class="mb-3">
                                    <label for="Act" class="form-label">Activity</label>
                                    <input type="text" class="form-control" id="Act" name="Act" value="${curDateAct.act}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="TimeStart" class="form-label">Time Start</label>
                                    <input type="time" class="form-control" id="TimeStart" name="TimeStart" value="${curDateAct.timeStart}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="TimeEnd" class="form-label">Time End</label>
                                    <input type="time" class="form-control" id="TimeEnd" name="TimeEnd" value="${curDateAct.timeEnd}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="CurDateID" class="form-label">Curriculum Date ID</label>
                                    <input type="number" class="form-control" id="CurDateID" name="CurDateID" value="${curDateAct.curDateID}" required>
                                </div>
                                <button type="submit" class="btn btn-primary" name="submit" value="submit">Update Activity</button>
                            </form>
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
