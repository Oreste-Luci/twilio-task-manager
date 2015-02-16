<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Task Home</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>
<body>

    <div class="container">

        <div class="jumbotron">
            <h1>Task Manager App</h1>
            <p>This application creates tasks by sending SMS messages to the task provider. The task provider can
            replay through a SMS accepting or rejecting the task.</p>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Available Functions</h3>
            </div>
            <div class="panel-body">
                <a href="sendTask.jsp" class="btn btn-primary btn-lg" role="button" style="width: 300px !important;">Create New Task</a>
                <br><br>
                <a href="taskList" class="btn btn-primary btn-lg" role="button" style="width: 300px !important;">List All Tasks</a>
            </div>
        </div>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>
