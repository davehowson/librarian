<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="includes/head.jsp"/>
    <body>
        <jsp:include page="includes/nav.jsp"/>
        <div class="container mt-3">
            <div class="row">
                <div class="col-sm-6">

                </div>
                <div class="col-sm-6">
                   <table class="table">
                       <tr>
                           <th>ISBN</th>
                           <td>${book.isbn}</td>
                       </tr>
                       <tr>
                           <th>Title</th>
                           <td>${book.title}</td>
                       </tr>
                       <tr>
                           <th>Author</th>
                           <td>${book.author}</td>
                       </tr>
                       <tr>
                           <th>Published</th>
                           <td>${book.date}</td>
                       </tr>
                   </table>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>
