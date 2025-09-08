<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
<div class="login-container">
    <h1>Iniciar Sesión</h1>
    <form action="login" method="post">
        <input type="text" name="usuario" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <button type="submit">Ingresar <i class="fa-solid fa-arrow-right-to-bracket"></i></button>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><i class="fa fa-exclamation-circle"></i> Error: Usuario o contraseña incorrectos</p>
        <% } %>
    </form>
</div>
</body>
</html>