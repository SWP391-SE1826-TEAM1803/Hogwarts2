<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.StudentSchoolYearClass, entity.Student1, entity.SchoolYear"%>
<%@page import="model.DAOStudent1, model.DAOSchoolYear"%>


<% 
    String SyC_ID = (String) request.getAttribute("SyC_ID");
    String cName = (String) request.getAttribute("cName");
    String cID = (String) request.getAttribute("cID");
    String syID = (String) request.getAttribute("syID");
    DAOStudent1 daoS = new DAOStudent1();
    DAOSchoolYear daoSY = new DAOSchoolYear();

    Vector<SchoolYear> sy = daoSY.getAllSchoolYears("SELECT * FROM SchoolYear Where SyID = '" + syID + "'");
    String syname = sy.get(0).getSyName();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title><%=cName%> - Hogwarts</title>
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
    <%@include file="HeaderAdmin.jsp"%>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>Class Detail</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="SchoolYearControllerURL?service=listAll">School Years</a></li>
                    <li class="breadcrumb-item"><a href="SchoolYearClassControllerURL?service=searchBySyID&SyID=<%= syID %>"><%=syname%></a></li>
                    <li class="breadcrumb-item active"><%=cName%></li>
                </ol>
            </nav>
        </div>

        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center">
                                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addStudentsModal">Add Students</button>
                            </div>

                            <table class="table table-borderless datatable mt-3">
                                <thead>
                                    <tr>
                                        <th scope="col">Full Name</th>
                                        <th scope="col">Date of Birth</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                       Vector<StudentSchoolYearClass> students = (Vector<StudentSchoolYearClass>) request.getAttribute("data");
                                       if (students != null) {
                                           for (StudentSchoolYearClass student : students) {
                                               Vector<Student1> stu = daoS.getAllStudents("SELECT * FROM Student WHERE StudentID = '" + student.getStudentID() + "'");
                                    %>
                                    <tr>
                                        <td><%= stu.get(0).getFullName() %></td>
                                        <td><%= stu.get(0).getDoB() %></td>
                                        <td><%= stu.get(0).getGender() %></td>
                                        <td><%= stu.get(0).getAddress() %></td>
                                        <td>
                                            <a class="btn btn-outline-danger btn-icon-text" href="StudentSYClassControllerURL?service=delete&studentId=<%= stu.get(0).getStudentID() %>&SyC_ID=<%=SyC_ID%>">
                                                <i class="mdi mdi-delete-forever"></i> Delete
                                            </a>
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
                </div>
            </div>
        </section>
    </main>

    <!-- Modal for adding students -->
    <div class="modal fade" id="addStudentsModal" tabindex="-1" aria-labelledby="addStudentsModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addStudentsModalLabel">Add Students</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Filter input for year of birth -->
                    <div class="mb-3">
                        <label for="filterYear" class="form-label">Filter by Year of Birth</label>
                        <select class="form-select" id="filterYear" onchange="filterStudents()">
                            <option value="">All</option>
                            <% 
                                int currentYear = 2023; // Năm hiện tại
                                for (int year = currentYear - 6; year <= currentYear; year++) { 
                            %>
                            <option value="<%= year %>"><%= year %></option>
                            <% } %>
                        </select>
                    </div>

                    <form id="addStudentsForm" action="StudentSYClassControllerURL" method="post">
                        <input type="hidden" name="service" value="addStudents">
                        <input type="hidden" name="SyC_ID" value="<%= SyC_ID %>">
                        <table class="table table-bordered" id="studentsTable">
                            <thead>
                                <tr>
                                    <th>Select</th>
                                    <th>Full Name</th>
                                    <th>Date of Birth</th>
                                    <th>Gender</th>
                                    <th>Address</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                    DAOStudent1 daoStudent = new DAOStudent1();
                                    Vector<Student1> students2 = daoStudent.getAllStudents("SELECT s.StudentID, s.FullName, s.DoB, s.Gender, s.Address, s.UserID F"
                                            + "ROM Student s "
                                            + "LEFT JOIN Student_SchoolYear_Class ssc ON s.StudentID = ssc.StudentID AND ssc.SyC_ID IN ("
                                            + "    SELECT SyC_ID"
                                            + "    FROM SchoolYear_Class"
                                            + "    WHERE SyID = " + syID + ""
                                            + ")"
                                            + "WHERE ssc.SyC_ID IS NULL;");
                                    for (Student1 student : students2) {
                                %>
                                <tr>
                                    <td><input type="checkbox" name="studentIds" value="<%= student.getStudentID() %>"></td>
                                    <td><%= student.getFullName() %></td>
                                    <td><%= student.getDoB() %></td>
                                    <td><%= student.getGender() %></td>
                                    <td><%= student.getAddress() %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" form="addStudentsForm" class="btn btn-primary">Add Selected Students</button>
                </div>
            </div>
        </div>
    </div>

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
    <script>
        function filterStudents() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("filterYear");
            filter = input.value;
            table = document.getElementById("studentsTable");
            tr = table.getElementsByTagName("tr");

            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[2]; // Date of Birth column
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }       
            }
        }
    </script>
</body>

</html>
