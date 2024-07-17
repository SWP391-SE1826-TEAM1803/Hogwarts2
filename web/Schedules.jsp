<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.CurriculumDate, entity.CurDateAct, model.DAOCurDateAct" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String filterDate = (String) request.getAttribute("filterDate");
    if (filterDate == null || filterDate.isEmpty()) {
        filterDate = LocalDate.now().toString();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Schedules Management - Hogwarts</title>
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
            .equal-table th, .equal-table td {
                width: 25%; /* Adjust this value as needed */
                text-align: center;
            }
        </style>
    </head>

    <body>
        <%@include file="HeaderTeacher.jsp"%>
        <main id="main" class="main">
            <div class="pagetitle">
                <h1>Schedules Management</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item active">Schedules</li>
                    </ol>
                </nav>
            </div>
            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Schedules List</h5>

                                <a href="SchedulesControllerURL?service=addSchedule" class="btn btn-success mb-3">Choose New Schedule</a>
                                <table class="table table-bordered equal-table">
                                    <thead>
                                        <tr>
                                            <th scope="col" style="text-align: center;">Date</th>
                                            <th scope="col" style="text-align: center;">Date Number</th>
                                            <th scope="col" style="text-align: center;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="schedule" items="${schedulesList}">
                                            <tr>
                                                <td style="text-align: center;">${schedule.date}</td>
                                                <td style="text-align: center;">${schedule.dateNumber}</td>
                                                <td style="text-align: center;">
                                                    <a href="SchedulesControllerURL?service=editSchedule&schedulesID=${schedule.schedulesID}" class="btn btn-outline-info btn-sm">Edit</a>
                                                    <a href="SchedulesControllerURL?service=deleteSchedule&schedulesID=${schedule.schedulesID}" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure you want to delete this schedule?');">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

<!--                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Search Curriculum Date</h5>
                            <form action="SchedulesControllerURL" method="get" class="w-100">
                                <input type="hidden" name="service" value="search">
                                <div class="mb-3">
                                    <label for="searchDateNumber" class="form-label">Date Number:</label>
                                    <input type="text" id="searchDateNumber" name="searchDateNumber" class="form-control">
                                </div>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </form>
                        </div>
                    </div>
                </div>-->

                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Curriculum Detail Today</h5>
                            <!-- Curriculum Date List Table -->
                            <%
                                DAOCurDateAct daoCDA = new DAOCurDateAct();
                                Vector<CurriculumDate> curDates = (Vector<CurriculumDate>) request.getAttribute("curriculumDates");
                                if (curDates != null) {
                                    for (CurriculumDate curDate : curDates) {
                            %>
                            <h5 class="card-title"><%= curDate.getDateNumber() %></h5>
                            <table class="table table-bordered equal-table">
                                <thead>
                                    <tr>
                                        <th>Time Start</th>
                                        <th>Time End</th>
                                        <th>Activity</th>
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
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                            <%
                                    }
                                }
                            %>
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
