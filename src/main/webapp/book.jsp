<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="includes/head.jsp"/>
    <body>
        <jsp:include page="includes/nav.jsp"/>
        <div class="container mt-3">
            <div class="row">
                <div class="col-sm-12 text-center">
                    <h4>${bookGR.title}</h4>
                </div>
            </div>
            <div class="row mt-4 d-flex justify-content-center">
                <div class="col-sm-3">
                    <img class="mt-4" src="${bookGR.imageUrl}" alt="Book Cover">
                </div>
                <div class="col-sm-6">
                    <table class="table">
                        <tr>
                            <th>Author</th>
                            <td>${bookGR.author}</td>
                        </tr>
                        <tr>
                            <th>Title</th>
                            <td>${bookGR.description}</td>
                        </tr>
                    </table>
                    <p class="text-right">Source : <a href="https://www.goodreads.com" target="_blank">goodreads.com</a></p>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>
