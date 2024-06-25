<%-- 
    Document   : Home
    Created on : May 22, 2024, 1:28:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.User"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Admin - Hogwarts</title>
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
      .card-icon {
  width: 50px;
  height: 50px;
  background-color: #f0f0f0; /* or any preferred background color */
  font-size: 24px;
  color: #000; /* or any preferred icon color */
}

  </style>

  
</head>

<body>
    <%@include file="HeaderAdmin.jsp"%>
    <main id="main" class="main">
        <div class="pagetitle">
            <h1>User Management</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="HomeAdmin.jsp">Home</a></li>
                    <li class="breadcrumb-item active" ><a href="UserControllerURL?service=listAll">User Management</a></li>
                </ol>
            </nav>
        </div>

        <div class="container mt-3">
            <%
                String service = request.getParameter("service");
                if (service == null || service.equals("listAll")) {
            %>
            <!-- Accounts List -->
            <div class="card recent-sales overflow-auto">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <form method="get" action="UserControllerURL" class="form-inline">
                            <div class="form-group mb-0 d-inline-flex">
                                <select class="form-control mr-2" id="roleSelect" name="role">
                                    <option value="">All</option>
                                    <option value="Admin">Admin</option>
                                    <option value="Parent">Parents</option>
                                    <option value="Teacher">Teacher</option>
                                </select>
                                <button type="submit" class="btn btn-primary">Filter</button>
                            </div>
                        </form>
                        <a href="UserControllerURL?service=addUserForm" class="btn btn-success">Add New User</a>
                    </div>
                    <!-- Table -->
                    <table class="table table-borderless datatable mt-3">
                        <thead>
                            <tr>
                                <th scope="col">Full name</th>                                
                                <th scope="col">Gender</th>
                                <th scope="col">Address</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Email</th>
                                <th scope="col">Role</th>
                                <th scope="col">Password</th>
                                
                                
                                <th scope="col" style="text-align: center;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% Vector<User> vector = (Vector<User>)request.getAttribute("data");
                                if (vector != null) {
                                    for(User user : vector) {
                            %>
                            <tr>
                                <td><%= user.getFullName() %></td>                                                                                            
                                <td><%= user.getGender() %></td>
                                <td><%= user.getAddress() %></td>
                                <td><%= user.getPhone() %></td>
                                <td><%= user.getEmail() %></td>
                                <td><%= user.getRole() %></td>
                                <td><%= user.getPassword() %></td>
                                <td style="text-align: center;">
                                    <a class="btn btn-outline-warning btn-icon-text" href="UserControllerURL?service=updateUserForm&UserID=<%=user.getUserID()%>">
                                        <i class="mdi mdi-refresh"></i> Update
                                    </a>
                                    <a class="btn btn-outline-danger btn-icon-text" href="UserControllerURL?service=deleteUser&UserID=<%=user.getUserID()%>">
                                        <i class="mdi mdi-delete-forever"></i> Delete
                                    </a>
                                </td>
                            </tr>
                            <% }
                                } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <% 
                } else if (service.equals("addUserForm")) { 
            %>
            <!-- Insert User Form -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">New User Information</h5>
                    <form action="UserControllerURL" method="post">
                        <input type="hidden" name="service" value="addUser">
                        <div class="row mb-3">
                            <label for="FullName" class="col-sm-2 col-form-label">Full Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="FullName" name="FullName" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Gender" class="col-sm-2 col-form-label">Gender</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="Gender" name="Gender" required>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Other">Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Address" class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Address" name="Address" placeholder="1234 Main St" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Phone" class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Phone" name="Phone" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="Email" name="Email" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Role" class="col-sm-2 col-form-label">Role</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="Role" name="Role" required>
                                    <option value="Admin">Admin</option>
                                    <option value="Parent">Parent</option>
                                    <option value="Teacher">Teacher</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Password" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="Password" name="Password" required>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-secondary">Reset</button>
                        </div>
                    </form>
                </div>
            </div>
            <% 
                } else if (service.equals("updateUserForm")) { 
                    User user = (User) request.getAttribute("user");
            %>
            <!-- Update User Form -->
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Edit Information</h5>
                    <form action="UserControllerURL" method="post">
                        <input type="hidden" name="service" value="updateUser">
                        <input type="hidden" name="UserID" value="<%= user.getUserID() %>">
                        <div class="row mb-3">
                            <label for="FullName" class="col-sm-2 col-form-label">Full Name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="FullName" name="FullName" value="<%= user.getFullName() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Gender" class="col-sm-2 col-form-label">Gender</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="Gender" name="Gender" required>
                                    <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
                                    <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
                                    <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Address" class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Address" name="Address" value="<%= user.getAddress() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Phone" class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Phone" name="Phone" value="<%= user.getPhone() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="Email" name="Email" value="<%= user.getEmail() %>" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Role" class="col-sm-2 col-form-label">Role</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="Role" name="Role" required>
                                    <option value="Admin" <%= "Admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
                                    <option value="Parent" <%= "Parent".equals(user.getRole()) ? "selected" : "" %>>Parent</option>
                                    <option value="Teacher" <%= "Teacher".equals(user.getRole()) ? "selected" : "" %>>Teacher</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <label for="Password" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="Password" name="Password" value="<%= user.getPassword() %>" required>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" name="submit" value="updateUser" class="btn btn-primary mr-2">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
            <% } %>
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

</body>

</html>