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
        <title>Nuevo Repuesto de Pantalla</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo repuesto<br>de pantalla</h1>
            <h1></h1>
            <div class="row">
                <a href="crearTipoPantalla.jsp">Tipo de Pantalla</a>
                <a></a>
            </div>
            <div class="row">
                <a href="crearTamanioPantalla.jsp">Tamaño de Pantalla</a>
                <a></a>
            </div>
            <div class="row">
                <a href="crearCodigoPantalla">Código de Pantalla</a>
            </div>
            <h1></h1>
        </div>
    </body>
</html>