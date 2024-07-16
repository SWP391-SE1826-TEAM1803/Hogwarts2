<%@ page import="java.util.Vector" %>
<%@ page import="entity.ClassCategory" %>
<%@ page import="entity.Menu" %>
<%@ page import="model.DAOClassCategory" %>
<%@ page import="model.DAOMenu" %>

<%
    // Create and populate vectors
    DAOClassCategory daoClassCategory = new DAOClassCategory();
    DAOMenu daoMenu = new DAOMenu();
    
    Vector<ClassCategory> categories = daoClassCategory.getAllCategories();
    Vector<Menu> menus = daoMenu.getAllMenus();

    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
    String submittedCategoryName = (String) request.getAttribute("submittedCategoryName");
    String submittedMeal = (String) request.getAttribute("submittedMeal");
    String submittedDate = (String) request.getAttribute("submittedDate");
    String[] submittedMenuItems = (String[]) request.getAttribute("submittedMenuItems");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Select Menu For Class</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmSubmit(event) {
            event.preventDefault(); // Prevent form submission
            if (confirm("Do you want to submit the form?")) {
                document.getElementById("menuForm").submit(); // Submit the form if user confirms
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Select Menu For Class</h1>
        
        <% if (successMessage != null) { %>
            <div class="alert alert-success" role="alert">
                <%= successMessage %>
            </div>
            <h3>Submitted Data</h3>
            <p><strong>Category:</strong> <%= submittedCategoryName %></p>
            <p><strong>Meal:</strong> <%= submittedMeal %></p>
            <p><strong>Date:</strong> <%= submittedDate %></p>
            <p><strong>Menu Items:</strong></p>
            <ul>
                <% for (String menuItem : submittedMenuItems) { 
                    for (Menu menu : menus) { 
                        if (menu.getMenuID() == Integer.parseInt(menuItem)) { %>
                            <li><%= menu.getFood() %></li>
                <%      }
                    }
                } %>
            </ul>
        <% } else if (errorMessage != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <% } %>
        
        <form id="menuForm" action="menuManager" method="post">
            <div class="mb-3">
                <label for="age" class="form-label">Select Age</label>
                <select class="form-select" id="age" name="age">
                    <% for (ClassCategory category : categories) { %>
                        <option value="<%= category.getCateID() %>"><%= category.getCateName() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="meal" class="form-label">Select Meal</label>
                <select class="form-select" id="meal" name="meal">
                    <option value="Breakfast">Breakfast</option>
                    <option value="Lunch">Lunch</option>
                    <option value="Dinner">Dinner</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="date" class="form-label">Select Date</label>
                <input type="date" class="form-control" id="date" name="date">
            </div>
            <div class="mb-3">
                <label class="form-label">Menu</label>
                <div class="form-check">
                    <% for (Menu menu : menus) { %>
                        <input class="form-check-input" type="checkbox" name="menu" value="<%= menu.getMenuID() %>">
                        <label class="form-check-label"><%= menu.getFood() %></label><br>
                    <% } %>
                </div>
            </div>
            <button type="submit" class="btn btn-primary" onclick="confirmSubmit(event)">Submit</button>
        </form>
    </div>
</body>
</html>
