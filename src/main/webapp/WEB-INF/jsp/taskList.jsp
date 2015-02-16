<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Task List</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>
<body>

  <div class="container">

      <div class="jumbotron">
          <h1>Task List</h1>
          <p>This page displays the list of tasks stored in the system.</p>
          <p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>" role="button">Home</a></p>
      </div>

    <div class="panel panel-default">
      <div class="panel-heading">Task List</div>
      <table class="table">
          <tr>
              <th>#</th>
              <th>Name</th>
              <th>Phone</th>
              <th>Text</th>
              <th>Status</th>
              <th>Created</th>
              <th>Updated</th>
          </tr>
          <c:forEach var="task" items="${taskList}">
          <tr>
              <td><c:out value="${task.id}"/></td>
              <td><c:out value="${task.name}"/></td>
              <td><c:out value="${task.phone}"/></td>
              <td><c:out value="${task.body}"/></td>
              <td><c:out value="${task.status}"/></td>
              <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${task.created}"/></td>
              <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${task.updated}"/></td>
          </tr>
          </c:forEach>
      </table>
    </div>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>
