<html>
    <head>
        <title>Librarian</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=PT+Serif|Dancing+Script|Roboto" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
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

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    </body>
</html>
