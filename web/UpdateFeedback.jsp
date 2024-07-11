<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Feedback" %>
<%@ page import="model.DAOFeedback" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Add Feedback - Your Application</title>
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
                <h1>Update Feedback</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="StudentControllerURL?service=listTeacherKid">Home</a></li>
                        <li class="breadcrumb-item active">Update Feedback</li>
                    </ol>
                </nav>
            </div>

            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <%
                                    int id = Integer.parseInt(request.getParameter("id"));
                                    DAOFeedback dao = new DAOFeedback();
                                    Feedback feedback = dao.getFeedbackByIDUpdate(id);

                                    if (feedback != null) {
                                %>
                                <form action="FeedbackControllerURL" method="post">
                                    <div class="form-group">
                                        <label for="date">Date</label>
                                        <input type="date" class="form-control" id="date" name="date" value="<%= feedback.getDate() %>" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="studentName">Student Name</label>
                                        <input type="text" class="form-control" id="studentName" name="studentName" value="<%= feedback.getFullName() %>" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="content">Content</label>
                                        <textarea class="form-control" id="content" name="content" rows="3" required><%= feedback.getContent() %></textarea>
                                    </div>
                                    <input type="hidden" name="service" value="updateFeedback">
                                    <input type="hidden" name="id" value="<%= feedback.getFeedbackID() %>">
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </form>
                                <% } else { %>
                                <p>Feedback not found.</p>
                                <% } %>
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
