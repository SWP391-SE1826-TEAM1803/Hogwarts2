<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.CurriculumDate, entity.CurDateAct, model.DAOCurDateAct" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    //String filterDate = (String) request.getAttribute("filterDate");
    //if (filterDate == null || filterDate.isEmpty()) {
    //    filterDate = LocalDate.now().plusDays(1).toString(); // Set filterDate to tomorrow's date
    //}
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Schedules - Hogwarts</title>
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
        <script>
            function setDateField() {
                var dateField = document.getElementById('date');
                var today = new Date();
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0');
                var yyyy = today.getFullYear();

                // Current date
                var currentDate = yyyy + '-' + mm + '-' + dd;

                // Tomorrow's date
                //today.setDate(today.getDate() + 1);
                //var ddTomorrow = String(today.getDate()).padStart(2, '0');
                //var mmTomorrow = String(today.getMonth() + 1).padStart(2, '0');
                //var yyyyTomorrow = today.getFullYear();
                //var tomorrowDate = yyyyTomorrow + '-' + mmTomorrow + '-' + ddTomorrow;

                // Set the date field to current date or tomorrow's date
                dateField.value = currentDate; // or tomorrowDate
            }

            window.onload = setDateField;
        </script>
    </head>
    <body>
        <%@include file="HeaderTeacher.jsp"%>
        <main id="main" class="main">
            <div class="pagetitle">
                <h1>Choose a Curriculum</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="SchedulesControllerURL?service=viewSchedules">Schedules</a></li>
                        <li class="breadcrumb-item active">Choose Curriculum</li>
                    </ol>
                </nav>
            </div>
            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Choose a Curriculum</h5>
                                <c:if test="${not empty errorMessage}">
                                    <div class="alert alert-danger">${errorMessage}</div>
                                </c:if>
                                <form action="SchedulesControllerURL" method="post" class="w-100">
                                    <input type="hidden" name="service" value="insertSchedule">
                                    <div class="mb-3">
                                        <label for="date" class="form-label">Date:</label>
                                        <input type="date" id="date" name="date" class="form-control" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="curDateID" class="form-label">Curriculum Date:</label>
                                        <select class="form-control" id="curDateID" name="curDateID" required>
                                            <c:forEach var="curriculumDate" items="${curriculumDates}">
                                                <option value="${curriculumDate.curDateID}">${curriculumDate.dateNumber} - ${curriculumDate.curName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit Schedule</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12">
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
                    </div>

                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Curriculum List</h5>
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
