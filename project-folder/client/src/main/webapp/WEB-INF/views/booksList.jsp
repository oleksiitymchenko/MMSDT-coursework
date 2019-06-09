<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Books</title>
</head>
<jsp:include page="menu.jsp" />
<body>

<div style="height:100%; background-image: url('https://hdwallpaperspage.com/wall/full/247908.jpg'); background-repeat: no-repeat;">
    <div class="my-2">
     <h3 class="text-center display-4 mb-3 text-light">Books</h3>
    <button type="button" class="btn btn-outline-success btn-lg col-2 offset-5 mb-2" data-toggle="modal" data-target="#exampleModalCenter">Create new</button>
    </div>
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-name" id="exampleModalLongTitle">Add a new book</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="POST" action="/books/create">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="title" class="col-form-label">Name:</label>
                            <input type="text" name="title" class="form-control" id="title">
                        </div>
                        <div class="form-group">
                            <label for="year" class="col-form-label">Year:</label>
                            <input type="number" name="year" class="form-control" id="year">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
                        <button class="btn btn-outline-success"  type="submit"> Add </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <table class="table table-striped table-dark">
        <thead class ="thead-dark">
        <tr class="text-uppercase text-center">
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Year</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach  items="${books}" var ="book">
            <tr class="text-center">
                <th scope="row">${book.id}</th>
                <td>${book.title}</td>
                <td>${book.year}</td>
                <td class="w-25">
                    <div>
                        <button type="button" class="btn btn-outline-warning" onclick="window.location.href='/books/${book.id}'" style="margin-right: 10px">Detail</button>
                        <button type="button" class="btn btn-outline-danger" onclick="window.location.href='/books/delete/${book.id}'" >Delete</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
