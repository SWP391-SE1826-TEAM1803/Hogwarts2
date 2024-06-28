<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Menu</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Update Menu</h1>

        <%
            Menu menu = (Menu) request.getAttribute("menu");
            if (menu != null) {
        %>
        <!-- Update Menu Form -->
        <div class="card mb-4">
            <div class="card-header">
                Update Menu
            </div>
            <div class="card-body">
                <form action="MenuControllerURL" method="post">
                    <input type="hidden" name="service" value="update">
                    <input type="hidden" name="submit" value="submit">
                    <input type="hidden" name="MenuID" value="<%= menu.getMenuID() %>">
                    <div class="form-group">
                        <label for="Food">Food Name</label>
                        <input type="text" class="form-control" id="Food" name="Food" value="<%= menu.getFood() %>" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Menu</button>
                </form>
            </div>
        </div>
        <%
            } else {
        %>
        <div class="alert alert-danger" role="alert">
            Menu item not found.
        </div>
        <%
            }
        %>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
