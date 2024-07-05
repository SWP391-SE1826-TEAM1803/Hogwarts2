<%-- 
    Document   : Home
    Created on : May 22, 2024, 1:28:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Student1" %>
<%@ page import="java.util.Vector, entity.Schedules, entity.CurriculumDate, entity.CurDateAct" %>
<%@ page import=" model.DAOSchedules, model.DAOCurriculumDate, model.DAOCurDateAct, model.DAOStudentSchoolYearClass" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Home - Hogwarts</title>
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
    
    <%@include file="HeaderParents.jsp"%>
   <%      Student1 stu = (Student1) request.getAttribute("stu");
   
%>
     <main id="main" class="main">
       
    <h5><%=stu.getFullName()%></h5>
<section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <table class="table table-borderless datatable">
                                <thead>
                                    <tr>
                                        <th scope="col">Date</th>
                                        <th scope="col">From</th>
                                        <th scope="col">To</th>
                                        <th scope="col">Act</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                    DAOSchedules dao = new DAOSchedules();
                    DAOStudentSchoolYearClass daoSSC = new DAOStudentSchoolYearClass();
                     DAOCurriculumDate daoCD = new DAOCurriculumDate();
                                        DAOCurDateAct daoCDA = new DAOCurDateAct();
                Vector<StudentSchoolYearClass> vectorSSC = daoSSC.getAllStudentSchoolYearClasses("select * from Student_SchoolYear_Class where StudentID = '" + stu.getStudentID() + "'");
             StudentSchoolYearClass SSyClass = vectorSSC.get(0);
            Vector<Schedules> schedules = dao.getAllSchedules("select * from Schedules where SyC_ID = '"+SSyClass.getSyC_ID()+"'");                            

                        for (Schedules schedule : schedules) {
                       
                        Vector<CurDateAct> vectorCurDateAct = daoCDA.getAllCurDateActs("SELECT * FROM [CurDateAct] WHERE CurDateID = '" + schedule.getCurDateID() + "'");
                        for (CurDateAct curDateAct : vectorCurDateAct){

                    %>
                                    <tr>
                                        <td><%= schedule.getDate() %></td>
                                        <td><%= curDateAct.getTimeStart() %></td>
                                        <td><%= curDateAct.getTimeEnd() %></td>
                                        <td><%= curDateAct.getAct() %></td>
                                        
                                        
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