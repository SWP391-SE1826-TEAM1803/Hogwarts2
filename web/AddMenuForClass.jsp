<%@ page import="java.util.Vector" %>
<%@ page import="entity.ClassCategory" %>
<%@ page import="entity.Menu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Select Menu for Class Category</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>
<body>
    <div class="container">
        <h2>Select Menu for Class Category</h2>
        <form action="menuManager" method="post">
            <div class="form-group">
                <label>Select Category Name:</label>
                <div>
                    <c:forEach var="category" items="${categories}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="cateID" value="${category.cateID}" onclick="showAgeName(${category.cateID});">
                            <label class="form-check-label">${category.cateName}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <label>Age Name:</label>
                <input type="text" id="ageName" class="form-control" readonly>
            </div>
            <div class="form-group">
                <label>Menu for Age Name:</label>
                <div id="menuList"></div>
            </div>
            <div class="form-group">
                <label>Select Date:</label>
                <input type="text" id="datePicker" name="date" class="form-control" readonly>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

    <!-- Modal for Date Picker -->
    <div class="modal fade" id="datePickerModal" tabindex="-1" role="dialog" aria-labelledby="datePickerModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="datePickerModalLabel">Select Date</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="text" id="modalDatePicker" class="form-control">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="setDate()">Set Date</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function showAgeName(cateID) {
            console.log("Selected Category ID: " + cateID); // Debug log
            $.ajax({
                url: 'menuManager?action=getMenus',
                type: 'GET',
                data: {cateID: cateID},
                success: function(response) {
                    console.log("Response from server: ", response); // Debug log
                    const data = response;
                    document.getElementById('ageName').value = data.ageName || "Unknown";
                    document.getElementById('menuList').innerHTML = '';
                    data.menus.forEach(menu => {
                        document.getElementById('menuList').innerHTML += `
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="menuID" value="${menu.menuID}">
                                <label class="form-check-label">${menu.food}</label>
                            </div>`;
                    });
                },
                error: function(error) {
                    console.error("Error fetching menus: ", error); // Debug log
                }
            });
        }

        $('#datePicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        }).on('show', function(e) {
            $('#datePickerModal').modal('show');
        });

        $('#modalDatePicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });

        function setDate() {
            const selectedDate = $('#modalDatePicker').val();
            $('#datePicker').val(selectedDate);
            $('#datePickerModal').modal('hide');
        }
    </script>
</body>
</html>
