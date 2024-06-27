<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.Class" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Add Student</h1>
    <form action="StudentControllerURL" method="post">
        <input type="hidden" name="service" value="addStudent">
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" required><br>
        
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" required><br>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="" disabled selected>Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>
        
        <label for="class">Class:</label>
        <select id="class" name="classID" required>
            <option value="" disabled selected>Select Class</option>
            <%
                Vector<Class> classes = (Vector<Class>) request.getAttribute("classes");
                if (classes != null) {
                    for (Class cl : classes) {
            %>
            <option value="<%= cl.getClassID() %>"><%= cl.getClassName() %></option>
            <%
                    }
                }
            %>
        </select><br>
        
        <label for="parent">Parent:</label>
        <select id="parent" name="userID" required>
            <%
                Vector<User> users = (Vector<User>) request.getAttribute("users");
                if (users != null) {
                    for (User user : users) {
            %>
            <option value="<%= user.getUserID() %>"><%= user.getUserID() %>-<%= user.getFullName() %></option>
            <%
                    }
                }
            %>
        </select><br>
        
        <input type="submit" value="Add Student">
    </form>
</body>
</html>
