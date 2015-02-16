<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title>Task Confirmation</title>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>
<body>

<div class="container">

  <div class="jumbotron">
    <h1>Create New Task Result</h1>
    <p>Shows the result of sending the task.</p>
    <p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>" role="button">Home</a></p>
  </div>

    <c:if test="${error=='true'}">
      <div class="alert alert-danger" role="alert">
        <c:out value="${outMessage}"/>
        <br><br>
        <a href="javascript:window.history.back();" class="btn btn-warning btn-default" role="button">Back</a>
      </div>
    </c:if>
    <c:if test="${error!='true'}">
      <div class="alert alert-success" role="alert">
        <c:out value="${outMessage}"/>
      </div>
    </c:if>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>