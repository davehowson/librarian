<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dave
  Date: 2/5/19
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Librarian - Results</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=PT+Serif|Dancing+Script|Roboto" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container mt-3">
        <div class="row">
            <div class="col-sm-6">
                <h1 class="mb-3">Search Results</h1>
                <form class="text-center" method="POST" action="SearchServlet" id="searchForm">
                    <div class="input-group mt-4">
                        <input type="text" class="form-control" id="param" name="param" placeholder="Search Book or Author" value='<c:out value="${param.param}"/>'>
                        <input type="hidden" name="isAjax" value="true">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-outline-dark">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:choose>
            <c:when test="${not empty bookList}">
                <table class="table" id="searchResults">
                    <thead>
                        <tr>
                            <th style="width: 15%">ISBN</th>
                            <th style="width: 50%">Title</th>
                            <th style="width: 20%">Author</th>
                            <th style="width: 15%">Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${bookList}">
                            <tr>
                                <td>${book.isbn}</td>
                                <td>${book.title}</td>
                                <td>${book.author}</td>
                                <td>${book.date}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    No Books Found
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <script>
        $(function () {
              $('#searchForm').submit(function (e) {
                  e.preventDefault();
                  var form = $(this);
                  var url = form.attr('action');

                  $.ajax({
                      type: "POST",
                      url: url,
                      data: form.serialize(),
                      success: function (data) {
                          populateTable(data);
                      },
                      error: function (data) {
                          console.log(data);
                      }
                  });

                  function populateTable(data) {
                      $('#searchResults > tbody').empty();

                      var cols = Object.keys(data[0]);
                      var headerRow = '';
                      var bodyRows = '';

                      cols.map(function(col){
                          headerRow += '<th scope="col">' + col + '</th>';
                      });

                      data.map(function(row){
                          bodyRows += '<tr>';
                          cols.map(function(colName){
                              bodyRows += '<td>' + row[colName] + '</td>'
                          });
                          bodyRows += '</tr>'
                      })

                      $('#searchResults > tbody').append(bodyRows);
                  }

              })
        })
    </script>
</body>
</html>
