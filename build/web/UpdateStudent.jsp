<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="entity.StudentSchoolYearClass" %>
<%@ page import="entity.Class" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Update Student</h1>
    <form action="StudentControllerURL" method="post">
        <input type="hidden" name="service" value="updateStudent">
        <input type="hidden" name="studentID" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getStudentID() %>">
        
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getFullName() %>" required><br>
        
        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getDoB() %>" required><br>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="Male" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getGender().equals("Male") ? "selected" : "" %>>Male</option>
            <option value="Female" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getGender().equals("Female") ? "selected" : "" %>>Female</option>
        </select><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getAddress() %>" required><br>
        
        <label for="class">Class:</label>
        <select id="class" name="classID" required>
            <%
                Vector<Class> classes = (Vector<Class>) request.getAttribute("classes");
                if (classes != null) {
                    for (Class cl : classes) {
            %>
            <option value="<%= cl.getClassID() %>" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getSchoolYearClass().getClassObj().getClassID() == cl.getClassID() ? "selected" : "" %>><%= cl.getClassName() %></option>
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
            <option value="<%= user.getUserID() %>" <%= ((StudentSchoolYearClass) request.getAttribute("studentClass")).getStudent().getParent().getUserID() == user.getUserID() ? "selected" : "" %>><%= user.getUserID() %>-<%= user.getFullName() %></option>
            <%
                    }
                }
            %>
        </select><br>
        
        <input type="submit" value="Update Student">
    </form>
</body>
</html>
