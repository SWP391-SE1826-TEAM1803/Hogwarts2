
<%@ page import="java.util.Vector" %>
<%@ page import="entity.Curriculum" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Curriculum Manager - Hogwarts</title>
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
            <h1>Curriculum Manager</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                    <li class="breadcrumb-item active">Curriculum Manager</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Add New Curriculum</h5>
                            <!-- Add New Curriculum Form -->
                            <form action="CurriculumControllerURL" method="post">
                                <input type="hidden" name="service" value="addCurriculum">
                                <div class="mb-3">
                                    <label for="CurName" class="form-label">Curriculum Name</label>
                                    <input type="text" class="form-control" id="CurName" name="CurName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="CateID" class="form-label">Category ID</label>
                                    <input type="number" class="form-control" id="CateID" name="CateID" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Curriculum</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Curriculum List</h5>
                            <!-- Curriculum List Table -->
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Category ID</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        // Retrieve the curriculum data from the request attribute
                                        Vector<Curriculum> curriculums = (Vector<Curriculum>) request.getAttribute("data");
                                        if (curriculums != null && !curriculums.isEmpty()) {
                                            for (Curriculum curriculum : curriculums) {
                                    %>
                                    <tr>
                                        <td><%= curriculum.getCurID() %></td>
                                        <td><%= curriculum.getCurName() %></td>
                                        <td><%= curriculum.getCateID() %></td>
                                        <td>
                                            <a href="CurriculumDateControllerURL" class="btn btn-primary btn-sm">Details</a>
                                            <a href="CurriculumControllerURL?service=delete&CurID=<%= curriculum.getCurID() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this curriculum?')">Delete</a>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        } else {
                                    %>
                                    <tr>
                                        <td colspan="4" class="text-center">No curriculums found.</td>
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
