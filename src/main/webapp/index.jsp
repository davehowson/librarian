<html>
    <jsp:include page="includes/head.jsp"/>
    <body>
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <form class="col-6 text-center" method="POST" action="SearchServlet">
                    <p class="subtitle m-0">Welcome to the</p>
                    <h2 class="display-2 title">LIBRARY</h2>
                    <div class="input-group mt-4">
                        <input type="text" class="form-control" id="param" name="param" placeholder="Search Book or Author">
                        <input type="hidden" name="isAjax" value="false">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-outline-dark">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    <jsp:include page="includes/footer.jsp"/>
    </body>
</html>
