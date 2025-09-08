<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Página Principal</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
  </head>
  <body>
    <!-- Menú desplegable -->
    <div class="menu-btn" onclick="toggleMenu()">☰</div>
    <div id="sidebar" class="sidebar">
        <t>Menú</t>
        <a href="index.jsp">Inicio</a>
        <t></t>
        <a href="opciones">Opciones</a>
    </div>

    <h1>¡Bienvenido a mi intento de aplicación web!</h1>

    <script>
    function toggleMenu() {
        document.getElementById("sidebar").classList.toggle("active");
    }
    </script>

  </body>
</html>