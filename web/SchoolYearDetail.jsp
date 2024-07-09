<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.User,model.DAOUser, entity.SchoolYearClass, entity.TeacherSchoolYearClass, entity.SchoolYear, entity.Class,entity.Teacher ,model.DAOSchoolYearClass, model.DAOSchoolYear, model.DAOClass, model.DAOTeacherSchoolYearClass, model.DAOTeacher"%>
<%@page import="entity.Curriculum, model.DAOCurriculum"%>
<% String syname = (String) request.getAttribute("syname");%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title><%=syname%> - Hogwarts</title>
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
            <h1>School Year Detail</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="SchoolYearControllerURL?service=listAll">School Years</a></li>
                    <li class="breadcrumb-item active"><%=syname%></li>
                </ol>
            </nav>
        </div>
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">School Year Information</h5>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addSchoolYearClassModal">
                        Add School Year Class
                    </button>
                    <table class="table table-borderless datatable">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Class ID</th>
                                <th scope="col">Class Name</th>
                                <th scope="col">Curriculum ID</th>
                                <th scope="col">Teacher</th>
                                <th scope="col" style="text-align: center;">Detail</th>
                                <th scope="col" style="text-align: center;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%  
                                DAOClass dao = new DAOClass();
                                DAOTeacherSchoolYearClass daoTSC = new DAOTeacherSchoolYearClass();
                                DAOTeacher daoT = new DAOTeacher();
                                DAOUser daoU = new DAOUser();
                                DAOCurriculum daoC = new DAOCurriculum();
                                Vector<SchoolYearClass> syClasses = (Vector<SchoolYearClass>) request.getAttribute("data");
                                for (SchoolYearClass syClass : syClasses) {
                                    Class classObj = dao.getClassByID(syClass.getClassID());
                                    TeacherSchoolYearClass teachersObj = daoTSC.getTeacherSchoolYearClassesBySyC_ID(syClass.getSyC_ID());
                                    Teacher teacher = daoT.getTeacherByID(teachersObj.getTeacherID());
                                    User user = daoU.getUserByID(teacher.getUserID());
                                    Curriculum cur = daoC.getCurriculumByID(syClass.getCurID());
                            %>
                            <tr>
                                <td><%= syClass.getSyC_ID() %></td>
                                <td><%= syClass.getClassID() %></td>
                                <td><%= classObj != null ? classObj.getClassName() : "N/A" %></td>
                                <td><%= cur != null ? cur.getCurName() : "N/A" %></td>
                                <td><%= teachersObj != null ? user.getFullName() : "N/A" %></td>
                                <td style="text-align: center;">
                                    <a class="btn btn-outline-info btn-icon-text" href="SchoolYearClassDetail.jsp?SyC_ID=<%=syClass.getSyC_ID()%>">
                                        <i class="mdi mdi-information"></i> Detail
                                    </a>                                    
                                </td>
                                <td style="text-align: center;">
                                    <button type="button" class="btn btn-outline-warning btn-icon-text" data-bs-toggle="modal" data-bs-target="#updateSchoolYearClassModal<%=syClass.getSyC_ID()%>">
                                        <i class="mdi mdi-refresh"></i> Update
                                    </button>
                                    <a class="btn btn-outline-danger btn-icon-text" href="SchoolYearClassControllerURL?service=deleteSchoolYearClass&SyC_ID=<%=syClass.getSyC_ID()%>&SyID=<%=syClass.getSyID()%>">
                                        <i class="mdi mdi-delete-forever"></i> Delete
                                    </a>
                                </td>
                            </tr>

                            <!-- Update School Year Class Modal -->
                            <div class="modal fade" id="updateSchoolYearClassModal<%=syClass.getSyC_ID()%>" tabindex="-1" aria-labelledby="updateSchoolYearClassModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="updateSchoolYearClassModalLabel">Update School Year Class</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form action="SchoolYearClassControllerURL" method="post">
                                            <div class="modal-body">
                                                <input type="hidden" name="service" value="updateSchoolYearClass">
                                                <input type="hidden" name="SyC_ID" value="<%=syClass.getSyC_ID()%>">
                                                <div class="mb-3">
                                                    <label for="SyID" class="form-label">School Year ID</label>
                                                    <input type="number" class="form-control" id="SyID" name="SyID" value="<%=syClass.getSyID()%>" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="ClassID" class="form-label">Class ID</label>
                                                    <input type="number" class="form-control" id="ClassID" name="ClassID" value="<%=syClass.getClassID()%>" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="CurID" class="form-label">Curriculum ID</label>
                                                    <input type="number" class="form-control" id="CurID" name="CurID" value="<%=syClass.getCurID()%>" required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
    <%@include file="Footer.jsp"%>

    <!-- Add School Year Class Modal -->
    <div class="modal fade" id="addSchoolYearClassModal" tabindex="-1" aria-labelledby="addSchoolYearClassModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addSchoolYearClassModalLabel">Add School Year Class</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="SchoolYearClassControllerURL" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="service" value="addSchoolYearClass">
                        <div class="mb-3">
                            <label for="SyID" class="form-label">School Year ID</label>
                            <input type="number" class="form-control" id="SyID" name="SyID" required>
                        </div>
                        <div class="mb-3">
                            <label for="ClassID" class="form-label">Class ID</label>
                            <input type="number" class="form-control" id="ClassID" name="ClassID" required>
                        </div>
                        <div class="mb-3">
                            <label for="CurID" class="form-label">Curriculum ID</label>
                            <input type="number" class="form-control" id="CurID" name="CurID" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add School Year Class</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

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
