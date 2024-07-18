<%-- 
    Document   : Home
    Created on : May 22, 2024, 1:28:18 PM
    Author     : Admin
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="entity.Student1" %>
<%@ page import="java.util.Vector, entity.Schedules, entity.CurriculumDate, entity.CurDateAct, entity.Menu, entity.ClassCategoryMenu, entity.Class, entity.SchoolYearClass,entity.Feedback" %>
<%@ page import="model.DAOSchedules, model.DAOCurriculumDate, model.DAOCurDateAct, model.DAOStudentSchoolYearClass, model.DAOSchoolYearClass, model.DAOClass, model.DAOClassCategoryMenu, model.DAOMenu, model.DAOFeedback" %>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList" %>
<%@ page import="java.util.Map.Entry" %>
 <%
     Student1 stu = (Student1) request.getAttribute("stu");
    String filterDate = (String) request.getAttribute("filterDate");
    String service = (String) request.getAttribute("service");
    Integer studentID = (Integer) request.getAttribute("studentID");
    if (filterDate == null || filterDate.isEmpty()) {
        filterDate = java.time.LocalDate.now().toString();
    }
    DAOSchedules dao = new DAOSchedules();
    DAOStudentSchoolYearClass daoSSC = new DAOStudentSchoolYearClass();
    DAOCurriculumDate daoCD = new DAOCurriculumDate();
    DAOCurDateAct daoCDA = new DAOCurDateAct();
    DAOSchoolYearClass daoSC = new DAOSchoolYearClass();
    DAOClass daoC = new DAOClass();
    DAOClassCategoryMenu daoCCM = new DAOClassCategoryMenu();
    DAOMenu daoM = new DAOMenu();
    DAOFeedback daoF = new DAOFeedback();

    Vector<StudentSchoolYearClass> vectorSSC = daoSSC.getAllStudentSchoolYearClasses("select * from Student_SchoolYear_Class where StudentID = '" + stu.getStudentID() + "' ORDER BY SyC_ID DESC");
    StudentSchoolYearClass SSyClass = vectorSSC.get(0);
%>

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
    
<main id="main" class="main">
    <h5>Student: <%=stu.getFullName()%></h5>
    <!-- Date Filter Form -->
       <form action="StudentControllerURL" method="get" class="mb-3">
            <input type="hidden" name="service" value="<%=service != null ? service : "listKid"%>">
            <% if ("viewStudent".equals(service) && studentID != null) { %>
                <input type="hidden" name="studentID" value="<%=studentID%>">
            <% } %>
            <label for="filterDate">Select Date:</label>
            <input type="date" id="filterDate" name="filterDate" value="<%=filterDate%>">
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
    <!-- Phần Lịch Trình -->
    <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Schedules</h5>
                        <table class="table table-borderless datatable">
                            <thead>
                                <tr>
                                    <th scope="col">From - To</th>
                                    <th scope="col">Act</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String scheduleQuery = "select * from Schedules where SyC_ID = '" + SSyClass.getSyC_ID() + "' and Date = '" + filterDate + "'";
                                    Vector<Schedules> schedules = dao.getAllSchedules(scheduleQuery);
                                    for (Schedules schedule : schedules) {
                                        Vector<CurDateAct> vectorCurDateAct = daoCDA.getAllCurDateActs("SELECT * FROM [CurDateAct] WHERE CurDateID = '" + schedule.getCurDateID() + "'");
                                        for (CurDateAct curDateAct : vectorCurDateAct) {
                                %>
                                <tr>
                                    <td><%= curDateAct.getTimeStart() %> - <%= curDateAct.getTimeEnd() %></td>
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
    
    <!-- Phần Menu -->
    <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Menu</h5>
<table class="table table-borderless datatable">
    <thead>
        <tr>
            <th scope="col">Meal</th>
            <th scope="col">Food</th>
        </tr>
    </thead>
    <tbody>
        <%
            // Retrieve the SchoolYearClass
            Vector<SchoolYearClass> vectorSC = daoSC.getAllSchoolYearClasses("select * from SchoolYear_Class where SyC_ID = '" + SSyClass.getSyC_ID() + "'");
            Vector<Class> vectorC = daoC.getAllClasses("select * from Class where ClassID = '" + vectorSC.get(0).getClassID() + "'");
            Vector<ClassCategoryMenu> vectorCCM = daoCCM.getAllClassCategoryMenus("select * from ClassCategory_Menu where CateID = '" + vectorC.get(0).getCateID() + "'");
            
            // Map to store meals and their corresponding food items
            Map<String, List<String>> mealToFoodsMap = new HashMap<>();
            
            // Collect food items for each meal
            for (ClassCategoryMenu ccMenu : vectorCCM) {
                if (!ccMenu.getDate().equals(filterDate)) {
                    continue;
                }
                
                Vector<Menu> menuVector = daoM.getAllMenus("select * from [Menu] where MenuID = '" + ccMenu.getMenuID() + "'");
                
                String meal = ccMenu.getMeal();
                if (!mealToFoodsMap.containsKey(meal)) {
                    mealToFoodsMap.put(meal, new ArrayList<>());
                }
                
                for (Menu menu : menuVector) {
                    mealToFoodsMap.get(meal).add(menu.getFood());
                }
            }
            
            // Display the meals and their corresponding food items
            for (Map.Entry<String, List<String>> entry : mealToFoodsMap.entrySet()) {
                String meal = entry.getKey();
                List<String> foods = entry.getValue();
                String foodList = String.join(", ", foods);
        %>
        <tr>
            <td><%= meal %></td>
            <td><%= foodList %></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!-- Phần Phản Hồi -->
    <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Feedback</h5>
                        <table class="table table-borderless datatable">
                            <thead>
                                <tr>
                                    <th scope="col">Content</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    String feedbackQuery = "SELECT * FROM Feedback where StudentID ='" + stu.getStudentID() + "' and Date = '" + filterDate + "'";
                                    Vector<Feedback> feedbacks = daoF.getAllFeedbacks(feedbackQuery);
                                    for (Feedback feedback : feedbacks) {
                                %>
                                <tr>
                                    <td><%= feedback.getContent() %></td>
                                </tr>
                                <%
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