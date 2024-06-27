<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Curriculum Date Activity</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="my-4">Update Curriculum Date Activity</h2>
        
        <div class="card">
            <div class="card-header">
                Update Curriculum Date Activity
            </div>
            <div class="card-body">
                <form action="CurDateActControllerURL" method="post">
                    <input type="hidden" name="service" value="update">
                    <input type="hidden" name="CdtID" value="${curDateAct.cdtID}">
                    <div class="form-group">
                        <label for="Act">Activity</label>
                        <input type="text" class="form-control" id="Act" name="Act" value="${curDateAct.act}" required>
                    </div>
                    <div class="form-group">
                        <label for="TimeStart">Time Start</label>
                        <input type="time" class="form-control" id="TimeStart" name="TimeStart" value="${curDateAct.timeStart}" required>
                    </div>
                    <div class="form-group">
                        <label for="TimeEnd">Time End</label>
                        <input type="time" class="form-control" id="TimeEnd" name="TimeEnd" value="${curDateAct.timeEnd}" required>
                    </div>
                    <div class="form-group">
                        <label for="CurDateID">Curriculum Date ID</label>
                        <input type="number" class="form-control" id="CurDateID" name="CurDateID" value="${curDateAct.curDateID}" required>
                    </div>
                    <button type="submit" class="btn btn-primary" name="submit" value="submit">Update Activity</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
