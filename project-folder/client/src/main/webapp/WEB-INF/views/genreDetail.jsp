<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Genre</title>
</head>
<jsp:include page="menu.jsp" />
<body class="text-light" style="background-image: url('https://hdwallpaperspage.com/wall/full/247908.jpg');">
    <h1 class="text-center">Genre Info</h1>
</div>
<div class="card mx-auto card-width bg-transparent">
    <form method="POST" action="/genres/${genre.id}">
    <div class="card-heading p-3"><h4>Genre id : ${genre.id}</h4></div>
    <div class="card-heading p-3">
        Title: ${genre.title} <br>
        <input class="form-control mt-3"
               placeholder="${genre.title}"
               name="title"/>
    </div>
        <div class="card-heading p-5 mx-auto" style="max-width: 70px">
            <button type="submit" class="btn btn-outline-light" href="/genres/update/${genre.id}">Update</button>
        </div>
    </form>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
