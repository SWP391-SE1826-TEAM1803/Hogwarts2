<%@ page import="java.util.Vector" %>
<%@ page import="entity.ClassCategory" %>
<%@ page import="entity.Menu" %>
<%@ page import="model.DAOClassCategory" %>
<%@ page import="model.DAOMenu" %>
<%@ page import="entity.ClassCategoryListMenu" %>
<%@ page import="java.util.List" %>
<%
    // Create and populate vectors
    DAOClassCategory daoClassCategory = new DAOClassCategory();
    DAOMenu daoMenu = new DAOMenu();
    
    Vector<ClassCategory> categories = daoClassCategory.getAllCategories();
    Vector<Menu> menus = daoMenu.getAllMenus();

    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
    List<ClassCategoryListMenu> todaySubmittedData = (List<ClassCategoryListMenu>) request.getAttribute("todaySubmittedData");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Select Menu For Class</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <%@include file="HeaderAdmin.jsp"%>
        <main id="main" class="main">
            <div class="container mt-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Select Menu For Class</h5>

                        <% if (successMessage != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= successMessage %>
                        </div>

                        <h3>Today</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Category Name</th>
                                    <th>Meal</th>
                                    <th>Date</th>
                                    <th>Menu</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (ClassCategoryListMenu data : todaySubmittedData) { %>
                                <tr>
                                    <td><%= data.getCateName() %></td>
                                    <td><%= data.getMeal() %></td>
                                    <td><%= data.getDate() %></td>
                                    <td>
                                        <% if (data.getNameFood() != null) { %>
                                        <ul>
                                            <% for (Object food : data.getNameFood()) { %>
                                            <li><%= food %></li>
                                                <% } %>
                                        </ul>
                                        <% } %>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
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
                                <input type="date" class="form-control" id="date" name="date" value="<%= new java.util.Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate() %>">
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
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="Footer.jsp"%>
    </body>
</html>
