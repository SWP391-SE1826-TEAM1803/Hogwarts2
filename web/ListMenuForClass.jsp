<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Class Category Menu</title>
    </head>
    <body>
        <h1>Class Category Menu</h1>
        <form action="ClassCategoryMenuServlet" method="get">
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required>
            <button type="submit">Search</button>
        </form>

        <table border="1">
            <thead>
                <tr>
                    <th>CateName</th>
                    <th>Date</th>
                    <th>Meal</th>
                    <th>Food</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${classCategoryMenuList}">
                    <tr>
                        <td>${item.cateName}</td>
                        <td>${item.date}</td>
                        <td>${item.meal}</td>
                        <td>${item.food}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Form to add menu item -->
        <h2>Select Menu For Class Category</h2>
        <form action="ClassCategoryMenuServlet" method="post">
            <input type="hidden" name="action" value="addMenu">
            <label for="className">Select Class Category:</label>
            <select id="className" name="className" class="form-control">
                <option value="">Select Class Category</option>
                <c:forEach var="category" items="${classCategories}">
                    <option value="${category.cateID}">${category.cateName}</option>
                </c:forEach>
            </select>
            <br><br>
            <label for="date">Select Date:</label>
            <input type="date" id="date" name="date" class="form-control" required>
            <br><br>
            <label for="meal">Select Meal:</label>
            <select id="meal" name="meal" class="form-control">
                <option value="">Select Meal</option>
                <option value="Breakfast">Breakfast</option>
                <option value="Lunch">Lunch</option>
                <option value="Dinner">Dinner</option>
            </select>
            <br><br>
            <label for="food">Select Food:</label>
            <select id="food" name="food" class="form-control">
                <option value="">Select Food</option>
                <c:forEach var="menu" items="${menus}">
                    <option value="${menu.menuID}">${menu.food}</option>
                </c:forEach>
            </select>
            <br><br>
            <input type="submit" value="Submit" class="btn btn-primary">
        </form>

    </body>
</html>
