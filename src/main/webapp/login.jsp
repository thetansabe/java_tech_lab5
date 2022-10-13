<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%
  //if there is session -> redirect to /
  if(session.getAttribute("userSession") != null){
    response.sendRedirect("/");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
        rel="stylesheet"
  />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<div class="container">
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-5">
      <h3 class="text-center text-secondary mt-5 mb-3">User Login</h3>

      <form action="/login" method="post" class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light">
        <div class="form-group">
          <label for="email">Email</label>
          <input id="email" name="email" type="email" class="form-control" placeholder="Username" value="${emailLoginCookie}">
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input id="password" name="password" type="password" class="form-control" placeholder="Password" value="${passLoginCookie}">
        </div>

        <%
          if(request.getAttribute("flashLogin") != null){
        %>
        <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
          <strong>${flashLogin.getIntro()}</strong> ${flashLogin.getMessage()}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>

        <div class="form-group">
          <button class="btn btn-success px-5">Login</button>
        </div>
        <div class="form-group">
          <input type="checkbox" name="remember" value="remember" id="remember">
          <label for="remember"> Remember username & password</label>
        </div>
        <div class="form-group">
          <p>Doesn't have account? <a href="/register">Register Now!</a></p>
        </div>
      </form>

    </div>
  </div>
</div>

</body>
</html>