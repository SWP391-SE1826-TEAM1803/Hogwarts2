<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.SchoolYear"%>
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
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i" rel="stylesheet">

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
            <h1>School Years Management</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active">School Years</li>
                </ol>
            </nav>
        </div>

        <div class="container mt-3">
            <%
                String service = request.getParameter("service");
                if (service == null || service.equals("listAll")) {
            %>
            <!-- School Years List -->
            <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                    <h5 class="card-title">School Years List</h5>
                    <a href="SchoolYearControllerURL?service=addSchoolYearForm" class="btn btn-success mb-3">Add New School Year</a>
                    </div>
                    <table class="table table-borderless datatable">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">End Date</th>
                                <th scope="col" style="text-align: center;">Detail</th>
                                <th scope="col" style="text-align: center;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Vector<SchoolYear> vector = (Vector<SchoolYear>) request.getAttribute("data");
                                if (vector != null) {
                                    for (SchoolYear schoolYear : vector) {
                            %>
                            <tr>
                                <td><%= schoolYear.getSyName() %></td>
                                <td><%= schoolYear.getDateStart() %></td>
                                <td><%= schoolYear.getDateEnd() %></td>
                                <td style="text-align: center;">
                                    <a class="btn btn-outline-info btn-icon-text" href="SchoolYearClassControllerURL?service=searchBySyID&SyID=<%= schoolYear.getSyID() %>">
                                        <i class="mdi mdi-information"></i> Detail
                                    </a>
                                    
                                </td>
                                <td style="text-align: center;">
                                    <a href="SchoolYearControllerURL?service=updateSchoolYearForm&SyID=<%= schoolYear.getSyID() %>" class="btn btn-outline-warning btn-icon-text">Update</a>
                                    <a href="SchoolYearControllerURL?service=deleteSY&SyID=<%= schoolYear.getSyID() %>" class="btn btn-outline-danger btn-icon-text">Delete</a>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>

            <% } else if (service.equals("addSchoolYearForm")) { %>
            <!-- Add School Year Form -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add School Year</h5>
                    <form action="SchoolYearControllerURL?service=addSchoolYear" method="POST">
                        <div class="mb-3">
                            <label for="SyName">School Year Name</label>
                            <input type="text" class="form-control" name="SyName" required>
                        </div>
                        <div class="mb-3">
                            <label for="DateStart">Start Date</label>
                            <input type="date" class="form-control" name="DateStart" required>
                        </div>
                        <div class="mb-3">
                            <label for="DateEnd">End Date</label>
                            <input type="date" class="form-control" name="DateEnd" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>

            <% } else if (service.equals("updateSchoolYearForm")) { 
                SchoolYear schoolYear = (SchoolYear) request.getAttribute("schoolYear");
            %>
            <!-- Update School Year Form -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Update School Year</h5>
                    <form action="SchoolYearControllerURL?service=updateSY" method="POST">
                        <input type="hidden" name="SyID" value="<%= schoolYear.getSyID() %>">
                        <div class="mb-3">
                            <label for="SyName">School Year Name</label>
                            <input type="text" class="form-control" name="SyName" value="<%= schoolYear.getSyName() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="DateStart">Start Date</label>
                            <input type="date" class="form-control" name="DateStart" value="<%= schoolYear.getDateStart() %>" required>
                        </div>
                        <div class="mb-3">
                            <label for="DateEnd">End Date</label>
                            <input type="date" class="form-control" name="DateEnd" value="<%= schoolYear.getDateEnd() %>" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
            <% } %>
        </div>
    </main>
    <%@include file="Footer.jsp"%>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/chart.js/chart.umd.js"></script>
    <script src="assets/vendor/echarts/echarts.min.js"></script>
    <script src="assets/vendor/quill/quill.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
</body>
</html>
