<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Create Task</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>
<body>

    <div class="container">

        <div class="jumbotron">
            <h1>Create New Task</h1>
            <p>This page allows for the creation of a new Task.</p>
            <p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>" role="button">Home</a></p>
        </div>

        <div class="col-md-6">
            <form action="smsSend" method="post" name="smsForm">

                <div class="form-group">
                    <label for="name">Enter recipients name</label>
                    <input type="text" class="form-control" id="name" placeholder="Please enter a name" name="name">
                </div>

                <div class="form-group">
                    <label for="number">Enter recipient's phone number</label>
                    <input type="text" class="form-control" id="number" placeholder="Please enter a phone number" name="phone">
                </div>

                <div class="form-group">
                    <label for="message">Enter recipient's phone number</label>
                    <textarea class="form-control" id="message" rows="3" name="message" placeholder="Enter message here"></textarea>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>

            </form>
        </div>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>