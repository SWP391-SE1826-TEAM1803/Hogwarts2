<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Curriculum Date Activity Manager</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="my-4">Curriculum Date Activity Manager</h2>

        <!-- Add Curriculum Date Activity Form -->
        <div class="card mb-4">
            <div class="card-header">
                Add New Curriculum Date Activity
            </div>
            <div class="card-body">
                <form action="CurDateActControllerURL" method="post">
                    <input type="hidden" name="service" value="addCurDateAct">
                    <div class="form-group">
                        <label for="Act">Activity</label>
                        <input type="text" class="form-control" id="Act" name="Act" required>
                    </div>
                    <div class="form-group">
                        <label for="TimeStart">Time Start</label>
                        <input type="text" class="form-control" id="TimeStart" name="TimeStart" required>
                    </div>
                    <div class="form-group">
                        <label for="TimeEnd">Time End</label>
                        <input type="text" class="form-control" id="TimeEnd" name="TimeEnd" required>
                    </div>
                    <div class="form-group">
                        <label for="CurDateID">Curriculum Date ID</label>
                        <input type="number" class="form-control" id="CurDateID" name="CurDateID" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Activity</button>
                </form>
            </div>
        </div>

        <!-- Curriculum Date Activity List -->
        <div class="card">
            <div class="card-header">
                Curriculum Date Activities List
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Activity ID</th>
                            <th>Activity</th>
                            <th>Time Start</th>
                            <th>Time End</th>
                            <th>Curriculum Date ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="curDateAct" items="${data}">
                            <tr>
                                <td>${curDateAct.cdtID}</td>
                                <td>${curDateAct.act}</td>
                                <td>${curDateAct.timeStart}</td>
                                <td>${curDateAct.timeEnd}</td>
                                <td>${curDateAct.curDateID}</td>
                                <td>
                                    <a href="CurDateActControllerURL?service=update&CdtID=${curDateAct.cdtID}" class="btn btn-warning btn-sm">Update</a>
                                    <a href="CurDateActControllerURL?service=delete&CdtID=${curDateAct.cdtID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
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
