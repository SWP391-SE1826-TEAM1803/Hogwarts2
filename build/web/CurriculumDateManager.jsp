<%@ page import="java.util.Vector" %>
<%@ page import="entity.CurriculumDate, entity.CurDateAct, model.DAOCurDateAct" %>
<% String curID = (String) request.getAttribute("curID"); %>

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

    <!-- Custom CSS for equal column and row sizes -->
    <style>
        .equal-table th, .equal-table td {
            width: 25%;
            text-align: center;
            vertical-align: middle;
        }
        .equal-table {
            table-layout: fixed;
        }
        .btn-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
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
                                    <input type="hidden" name="CurID" value="<%= curID %>">
                                </div>
                                <button type="submit" class="btn btn-primary">Add Curriculum Date</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <!-- Curriculum Date List Table -->
                            <%
                                DAOCurDateAct daoCDA = new DAOCurDateAct();
                                Vector<CurriculumDate> curDates = (Vector<CurriculumDate>) request.getAttribute("curriculumDate");
                                for (CurriculumDate curDate : curDates) {
                            %>
                            <div class="d-flex justify-content-between align-items-center">
                                <h5 class="card-title"><%= curDate.getDateNumber() %></h5>
                                <div class="btn-group">
                                    <button class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" data-bs-target="#addModal<%= curDate.getCurDateID() %>">New Action</button>
                                    <a href="CurriculumDateControllerURL?service=delete&CurDateID=<%= curDate.getCurDateID() %>&CurID=<%= curID %>" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                </div>
                            </div>
                            <table class="table table-bordered equal-table">
                                <thead>
                                    <tr>
                                        <th>Time Start</th>
                                        <th>Time End</th>
                                        <th>Activity</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        Vector<CurDateAct> vectorCurDateAct = daoCDA.getAllCurDateActs("SELECT * FROM CurDateAct WHERE CurDateID = '" + curDate.getCurDateID() + "'");
                                        for (CurDateAct curDateAct : vectorCurDateAct) {
                                    %>
                                    <tr>
                                        <td><%= curDateAct.getTimeStart() %></td>
                                        <td><%= curDateAct.getTimeEnd() %></td>
                                        <td><%= curDateAct.getAct() %></td>
                                        <td>
                                            <!-- Update and Delete Actions -->
                                            <a href="#" class="btn btn-outline-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateModal<%= curDateAct.getCdtID() %>">Update</a>
                                            <a href="CurriculumDateControllerURL?service=delete&CurDateID=<%= curDate.getCurDateID() %>&CurID=<%= curID %>" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>

                                            <!-- Update Modal -->
                                            <div class="modal fade" id="updateModal<%= curDateAct.getCdtID() %>" tabindex="-1" aria-labelledby="updateModalLabel<%= curDateAct.getCdtID() %>" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="updateModalLabel<%= curDateAct.getCdtID() %>">Update Curriculum Date Activity</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="CurDateActControllerURL" method="post">
                                                                <input type="hidden" name="service" value="updateCurDateAct">
                                                                <input type="hidden" name="CdtID" value="<%= curDateAct.getCdtID() %>">
                                                                <input type="hidden" name="CurID" value="<%= curID %>">
                                                                <div class="mb-3">
                                                                    <label for="TimeStart<%= curDateAct.getCdtID() %>" class="form-label">Time Start</label>
                                                                    <input type="text" class="form-control" id="TimeStart<%= curDateAct.getCdtID() %>" name="TimeStart" value="<%= curDateAct.getTimeStart() %>" required>
                                                                </div>
                                                                <div class="mb-3">
                                                                    <label for="TimeEnd<%= curDateAct.getCdtID() %>" class="form-label">Time End</label>
                                                                    <input type="text" class="form-control" id="TimeEnd<%= curDateAct.getCdtID() %>" name="TimeEnd" value="<%= curDateAct.getTimeEnd() %>" required>
                                                                </div>
                                                                <div class="mb-3">
                                                                    <label for="Act<%= curDateAct.getCdtID() %>" class="form-label">Activity</label>
                                                                    <input type="text" class="form-control" id="Act<%= curDateAct.getCdtID() %>" name="Act" value="<%= curDateAct.getAct() %>" required>
                                                                </div>
                                                                <button type="submit" class="btn btn-primary">Update</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>

                            <!-- Add Modal -->
                            <div class="modal fade" id="addModal<%= curDate.getCurDateID() %>" tabindex="-1" aria-labelledby="addModalLabel<%= curDate.getCurDateID() %>" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="addModalLabel<%= curDate.getCurDateID() %>">Add New Curriculum Date Activity</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="CurDateActControllerURL" method="post">
                                                <input type="hidden" name="service" value="addCurDateAct">
                                                <input type="hidden" name="CurDateID" value="<%= curDate.getCurDateID() %>">
                                                <input type="hidden" name="CurID" value="<%= curID %>">
                                                <div class="mb-3">
                                                    <label for="TimeStartNew<%= curDate.getCurDateID() %>" class="form-label">Time Start</label>
                                                    <input type="text" class="form-control" id="TimeStartNew<%= curDate.getCurDateID() %>" name="TimeStart" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="TimeEndNew<%= curDate.getCurDateID() %>" class="form-label">Time End</label>
                                                    <input type="text" class="form-control" id="TimeEndNew<%= curDate.getCurDateID() %>" name="TimeEnd" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="ActNew<%= curDate.getCurDateID() %>" class="form-label">Activity</label>
                                                    <input type="text" class="form-control" id="ActNew<%= curDate.getCurDateID() %>" name="Act" required>
                                                </div>
                                                <button type="submit" class="btn btn-primary">Add</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%
                                }
                            %>
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
