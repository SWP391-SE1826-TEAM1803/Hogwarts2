<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Curriculum Date Activity Manager</title>
    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
            <h1>Curriculum Date Activity Manager</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Curriculum Date Activity Manager</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card mb-4">
                            <div class="card-header">
                                Add New Curriculum Date Activity
                            </div>
                            <div class="card-body">
                                <form action="CurDateActControllerURL" method="post">
                                    <input type="hidden" name="service" value="addCurDateAct">
                                    <div class="form-group">
                                        <label for="Act">Activity</label>
                                        <input type="text" class="form-control" id="Act" name="Act" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="TimeStart">Time Start</label>
                                        <input type="time" class="form-control" id="TimeStart" name="TimeStart" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="TimeEnd">Time End</label>
                                        <input type="time" class="form-control" id="TimeEnd" name="TimeEnd" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="CurDateID">Curriculum Date ID</label>
                                        <input type="number" class="form-control" id="CurDateID" name="CurDateID" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Add Activity</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Curriculum Date Activities List</h5>
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Activity ID</th>
                                                <th>Activity</th>
                                                <th>Time Start</th>
                                                <th>Time End</th>
                                                <th>Curriculum Date ID</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="curDateAct" items="${data}">
                                                <tr>
                                                    <td>${curDateAct.cdtID}</td>
                                                    <td>${curDateAct.act}</td>
                                                    <td>${curDateAct.timeStart}</td>
                                                    <td>${curDateAct.timeEnd}</td>
                                                    <td>${curDateAct.curDateID}</td>
                                                    <td>
                                                        <a href="CurDateActControllerURL?service=update&CdtID=${curDateAct.cdtID}" class="btn btn-warning btn-sm">Update</a>
                                                        <a href="CurDateActControllerURL?service=delete&CdtID=${curDateAct.cdtID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty data}">
                                                <tr>
                                                    <td colspan="6" class="text-center">No curriculum date activities found.</td>
                                                </tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
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
