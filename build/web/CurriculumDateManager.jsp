<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Curriculum Date Manager</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="my-4">Curriculum Date Manager</h2>

        <!-- Add Curriculum Date Form -->
        <div class="card mb-4">
            <div class="card-header">
                Add New Curriculum Date
            </div>
            <div class="card-body">
                <form action="CurriculumDateControllerURL" method="post">
                    <input type="hidden" name="service" value="addCurriculumDate">
                    <div class="form-group">
                        <label for="DateNumber">Date Number</label>
                        <input type="text" class="form-control" id="DateNumber" name="DateNumber" required>
                    </div>
                    <div class="form-group">
                        <label for="CurID">Curriculum ID</label>
                        <input type="number" class="form-control" id="CurID" name="CurID" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Curriculum Date</button>
                </form>
            </div>
        </div>

        <!-- Curriculum Date List -->
        <div class="card">
            <div class="card-header">
                Curriculum Dates List
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Date ID</th>
                            <th>Date Number</th>
                            <th>Curriculum ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="curriculumDate" items="${data}">
                            <tr>
                                <td>${curriculumDate.curDateID}</td>
                                <td>${curriculumDate.dateNumber}</td>
                                <td>${curriculumDate.curID}</td>
                                <td>
                                    
                                    <a href="CurDateActControllerURL" class="btn btn-primary btn-sm">Details</a>
                                    <a href="CurriculumDateControllerURL?service=delete&CurDateID=${curriculumDate.curDateID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
