<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.StudentSchoolYearClass" %>
<%@ page import="entity.Class" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Update Student</title>

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

    <!-- Select2 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

    <!-- jQuery (necessary for Select2) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Select2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
</head>
<body>
    <%@include file="HeaderAdmin.jsp"%>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Student Management</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active"><a href="StudentControllerURL?service=listAll">Student Management</a></li>
                </ol>
            </nav>
        </div>

        <div class="container mt-3">
            <!-- Update Student Form -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Edit Student Information</h5>
                    <form action="StudentControllerURL" method="post">
                        <input type="hidden" name="service" value="updateStudent">
                        <input type="hidden" name="studentID" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getStudentID() %>">
                        <div class="row mb-3">
                            <label for="fullName" class="col-sm-2 col-form-label">Full Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="fullName" name="fullName" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getFullName() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="dob" class="col-sm-2 col-form-label">Date of Birth</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" id="dob" name="dob" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getDoB() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="gender" class="col-sm-2 col-form-label">Gender</label>
                            <div class="col-sm-10">
                                <select class="form-control " id="gender" name="gender" required>
                                    <option value="" disabled>Select Gender</option>
                                    <option value="Male" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getGender().equals("Male") ? "selected" : "" %>>Male</option>
                                    <option value="Female" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getGender().equals("Female") ? "selected" : "" %>>Female</option>
                                    <option value="Other" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getGender().equals("Other") ? "selected" : "" %>>Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="address" class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="address" name="address" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getAddress() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="classID" class="col-sm-2 col-form-label">Class</label>
                            <div class="col-sm-10">
                                <select class="form-control select2" id="classID" name="classID" required>
                                    <option value="" disabled>Select Class</option>
                                    <% 
                                        Vector<Class> classes = (Vector<Class>) request.getAttribute("classes");
                                        if (classes != null) {
                                            for (Class cl : classes) {
                                    %>
                                    <option value="<%= cl.getClassID() %>" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getSchoolYearClass().getClassObj().getClassID() == cl.getClassID() ? "selected" : "" %>><%= cl.getClassName() %> - <%= cl.getCateName() %></option>
                                    <% 
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="userID" class="col-sm-2 col-form-label">Parent</label>
                            <div class="col-sm-10">
                                <select class="form-control select2" id="userID" name="userID" required>
                                    <option value="" selected disabled>Select a parent</option>
                                    <% 
                                        Vector<User> users = (Vector<User>) request.getAttribute("users");
                                        if (users != null) {
                                            for (User user : users) {
                                    %>
                                    <option value="<%= user.getUserID() %>" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getParent().getUserID() == user.getUserID() ? "selected" : "" %>><%= user.getFullName() %> - <%= user.getPhone() %></option>
                                    <% 
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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

    <!-- Select2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
    
    <script>
        $(document).ready(function() {
            $('#classID').select2({
                placeholder: "Select Class",
                allowClear: false 
            });
            $('#userID').select2({
                placeholder: "Select a parent",
                allowClear: false 
            });
        });
        
        function resetForm() {
            $('#addStudentForm')[0].reset();
            $('#classID').val(null).trigger('change'); // Reset the Select2 element for class selection
            $('#userID').val(null).trigger('change'); // Reset the Select2 element for parent selection
        }

    </script>
</body>
</html>
