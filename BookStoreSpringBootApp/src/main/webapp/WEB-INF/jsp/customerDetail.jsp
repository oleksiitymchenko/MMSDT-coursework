<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer</title>
</head>
<jsp:include page="menu.jsp" />
<body>

<div class="row justify-content-center">
    <h1>Customer Info</h1>
</div>
<div class="card mx-auto card-width">
    <form method="post" action="/customers/update/${client.id}">
    <div class="card-heading p-2"><h4>client id : ${client.id}</h4></div>
    <div class="card-heading p-2">
        Name: ${client.title} <br>
        <input class="form-control"
               placeholder="${client.title}"
               title="title"/>
    </div>
    <div class="card-heading p-2">
        Surname: ${client.surname} <br> <input class="form-control"
                                               title="surname"
                                                 placeholder="${client.surname}"
                                                 />
    </div>
    <div class="card-heading p-2">
        Email : ${client.email}$
        <br>
        <input class="form-control"
               title="email"
               placeholder="${client.email}"
               />
    </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
