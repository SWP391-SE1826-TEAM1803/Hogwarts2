<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menu List</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Menu List in Hogwarts</h1>
        
        <!-- Add New Menu Form -->
        <div class="card mb-4">
            <div class="card-header">
                Add New Food
            </div>
            <div class="card-body">
                <form action="MenuControllerURL" method="post">
                    <input type="hidden" name="service" value="addMenu">
                    <div class="form-group">
                        <label for="Food">Food Name</label>
                        <input type="text" class="form-control" id="Food" name="Food" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Menu</button>
                </form>
            </div>
        </div>
        
        <!-- Menu List Table -->
        <table class="table table-bordered">
            <thead class="thead-light">
                <tr>
                    <th>ID</th>
                    <th>Food Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Vector<Menu> menus = (Vector<Menu>) request.getAttribute("data");
                    if (menus != null) {
                        for (Menu menu : menus) {
                %>
                <tr>
                    <td><%= menu.getMenuID() %></td>
                    <td><%= menu.getFood() %></td>
                    <td>
                        <a href="MenuControllerURL?service=update&MenuID=<%= menu.getMenuID() %>" class="btn btn-warning btn-sm">Update</a>
                        <a href="MenuControllerURL?service=delete&MenuID=<%= menu.getMenuID() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this menu?')">Delete</a>
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
