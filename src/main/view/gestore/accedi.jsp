<%--
  Project: MyAssistance
  Author: Aymen
  Date: 23/12/2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Accedi | Gestore </title>
  <!-- Bootstrap -->
  <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
</head>

<body class="bg-dark">
  <div class="container py-5">
    <div class="row">
      <div class="col-md-12">
        <h2 class="text-center mb-4 text-white">Gestore</h2>
        <div class="row">
          <div class="col-md-6 mx-auto">

            <!-- form card login -->
            <div class="card shadow p-3 mb-5 bg-white rounded">
              <div class="card-header d-flex justify-content-center row">
                <img src="/assets/images/logo.png" alt="logo" width="100" height="100">
                <h3 class="mb-0">Università degli Studi di Salerno </h3>
              </div>
              <div class="card-body">
                <form class="form" role="form" id="formLogin" method="POST">
                  <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control form-control-lg" autocomplete="username" id="usr">
                  </div>
                  <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control form-control-lg" autocomplete="current-password" id="pwd">
                  </div>
                  <button type="submit" class="btn btn-success btn-lg btn-block" id="btnLogin">Accedi</button>
                  <div class="text-center m-4">Se sei un utente <a href="/utente/accedi.jsp">clicca qui</a></div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>