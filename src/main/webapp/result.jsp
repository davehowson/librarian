<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="includes/head.jsp"/>
    <body>
        <jsp:include page="includes/nav.jsp"/>
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
            <div class="row">
                <table class="table table-hover" id="searchResults">
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
                        <tr class="clickable-row" data-isbn="${book.isbn}">
                            <td>${book.isbn}</td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="includes/footer.jsp"/>
        <script>
            $(function () {
                $(".clickable-row").click(function () {
                    var isbn = $(this).data("isbn");
                    window.location.href = "book?isbn="+isbn;
                });

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
        </script>
    </body>
</html>
