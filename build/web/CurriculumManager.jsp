<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.Curriculum" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Curriculum Manager</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Curriculum Manager</h1>
        
        <!-- Add New Curriculum Form -->
        <div class="card mb-4">
            <div class="card-header">
                Add New Curriculum
            </div>
            <div class="card-body">
                <form action="CurriculumControllerURL" method="post">
                    <input type="hidden" name="service" value="addCurriculum">
                    <div class="form-group">
                        <label for="CurName">Curriculum Name</label>
                        <input type="text" class="form-control" id="CurName" name="CurName" required>
                    </div>
                    <div class="form-group">
                        <label for="CateID">Category ID</label>
                        <input type="number" class="form-control" id="CateID" name="CateID" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Curriculum</button>
                </form>
            </div>
        </div>
        
        <!-- Curriculum List Table -->
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Vector<Curriculum> curriculums = (Vector<Curriculum>) request.getAttribute("data");
                    if (curriculums != null) {
                        for (Curriculum curriculum : curriculums) {
                %>
                <tr>
                    <td><%= curriculum.getCurID() %></td>
                    <td><%= curriculum.getCurName() %></td>
                    <td><%= curriculum.getCateID() %></td>
                    
                    <td>
<!--                        <a href="CurriculumControllerURL?service=update&CurID=<%= curriculum.getCurID() %>" class="btn btn-warning btn-sm">Update</a>-->
                        <a href="CurriculumDateControllerURL" class="btn btn-primary btn-sm">Details</a>
                        <a href="CurriculumControllerURL?service=delete&CurID=<%= curriculum.getCurID() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this curriculum?')">Delete</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
    
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
