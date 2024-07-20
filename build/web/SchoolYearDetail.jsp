<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.User,model.DAOUser, entity.SchoolYearClass, entity.TeacherSchoolYearClass, entity.SchoolYear, entity.Class,entity.Teacher ,model.DAOSchoolYearClass, model.DAOSchoolYear, model.DAOClass, model.DAOTeacherSchoolYearClass, model.DAOTeacher"%>
<%@page import="entity.Curriculum, model.DAOCurriculum"%>
<% String syname = (String) request.getAttribute("syname");%>
<% String syID = (String) request.getAttribute("syID");%>

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
                                        <input type="hidden" name="SyID" value="<%=syID%>">

                                        <div class="mb-3">
                                            <label for="ClassID" class="form-label">Class</label>
                                            <select class="form-control" id="ClassID" name="ClassID" required>
                                                <% 
                                                    DAOClass daoClass = new DAOClass();
                                                    Vector<Class> classes = daoClass.getAllClasses();
                                                    for (Class c : classes) {
                                                %>
                                                <option value="<%=c.getClassID()%>"><%=c.getClassName()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="CurID" class="form-label">Curriculum</label>
                                            <select class="form-control" id="CurID" name="CurID" required>
                                                <% 
                                                    DAOCurriculum daoCurriculum = new DAOCurriculum();
                                                    Vector<Curriculum> curriculums = daoCurriculum.getAllCurriculum();
                                                    for (Curriculum curriculum : curriculums) {
                                                %>
                                                <option value="<%=curriculum.getCurID()%>"><%=curriculum.getCurName()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="TeacherID" class="form-label">Teacher</label>
                                            <select class="form-control" id="TeacherID" name="TeacherID" required>
                                                <% 
                                                    DAOTeacher daoTeacher = new DAOTeacher();
                                                    DAOUser daoUser = new DAOUser();
                                                    Vector<Teacher> teachers = daoTeacher.getAllTeachers();
                                                    for (Teacher teacher : teachers) {
                                                %>
                                                <option value="<%=teacher.getTeacherID()%>"><%=daoUser.getUserByID(teacher.getUserID()).getFullName()%></option>
                                                <% } %>
                                            </select>
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

                    <!-- Update School Year Class Modal Template -->
                    <div class="modal fade" id="updateSchoolYearClassModalTemplate" tabindex="-1" aria-labelledby="updateSchoolYearClassModalTemplateLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updateSchoolYearClassModalTemplateLabel">Update School Year Class</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form action="SchoolYearClassControllerURL" method="post">
                                    <div class="modal-body">
                                        <input type="hidden" name="service" value="updateSchoolYearClass">
                                        <input type="hidden" id="updateSyC_ID" name="SyC_ID">
                                        <input type="hidden" id="updateSyID" name="SyID">

                                        <div class="mb-3">
                                            <label for="updateCurID" class="form-label">Curriculum</label>
                                            <select class="form-control" id="updateCurID" name="CurID" required>
                                                <% 
                                                    DAOCurriculum daoCurriculumUpdate = new DAOCurriculum();
                                                    Vector<Curriculum> curriculumsUpdate = daoCurriculumUpdate.getAllCurriculum();
                                                    for (Curriculum curriculum : curriculumsUpdate) {
                                                %>
                                                <option value="<%=curriculum.getCurID()%>"><%=curriculum.getCurName()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="updateTeacherID" class="form-label">Teacher</label>
                                            <select class="form-control" id="updateTeacherID" name="TeacherID" required>
                                                <% 
                                                    DAOTeacher daoTeacherUpdate = new DAOTeacher();
                                                    DAOUser daoUserUpdate = new DAOUser();
                                                    Vector<Teacher> teachersUpdate = daoTeacherUpdate.getAllTeachers();
                                                    for (Teacher teacher : teachersUpdate) {
                                                %>
                                                <option value="<%=teacher.getTeacherID()%>"><%=daoUserUpdate.getUserByID(teacher.getUserID()).getFullName()%></option>
                                                <% } %>
                                            </select>
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

                    <table class="table table-borderless datatable">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Class ID</th>
                                <th scope="col">Class Name</th>
                                <th scope="col">Curriculum</th>
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
                                    <a class="btn btn-outline-info btn-icon-text" href="StudentSYClassControllerURL?service=viewSYClass&SyC_ID=<%=syClass.getSyC_ID()%>&cID=<%=syClass.getClassID()%>&SyID=<%=syClass.getSyID()%>">
                                        <i class="mdi mdi-information"></i> Detail
                                    </a>                                    
                                </td>
                                <td style="text-align: center;">
                                    <button type="button" class="btn btn-outline-warning btn-icon-text" data-bs-toggle="modal" data-bs-target="#updateSchoolYearClassModalTemplate" onclick="fillUpdateForm(<%=syClass.getSyC_ID()%>, <%=syClass.getCurID()%>, <%=teachersObj.getTeacherID()%>, '<%=syClass.getSyID()%>')">
                                        <i class="mdi mdi-refresh"></i> Update
                                    </button>
                                    <a class="btn btn-outline-danger btn-icon-text" href="SchoolYearClassControllerURL?service=deleteSchoolYearClass&SyC_ID=<%=syClass.getSyC_ID()%>&SyID=<%=syClass.getSyID()%>">
                                        <i class="mdi mdi-delete-forever"></i> Delete
                                    </a>
                                </td>
                            </tr>

                            <% } %>
                        </tbody>
                    </table>
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

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
    <script>
        function fillUpdateForm(syC_ID, curID, teacherID, syID) {
            document.getElementById('updateSyC_ID').value = syC_ID;
            document.getElementById('updateCurID').value = curID;
            document.getElementById('updateTeacherID').value = teacherID;
            document.getElementById('updateSyID').value = syID;
        }
    </script>
</body>

</html>
