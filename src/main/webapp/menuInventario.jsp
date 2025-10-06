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
        <title>Inventario</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <div class="container">
            <h1>Inventario</h1>
            <h1></h1>

            <div class="row">
                <a href="verInventarioElectronico">Ver inventario de repuestos electrónicos</a>
                <a></a>
            </div>

            <div class="row">
                <a href="verInventarioPantalla">Ver inventario de repuestos de pantallas</a>
                <a></a>
            </div>

            <div class="row">
                <a href="verInventarioLed">Ver inventario de leds</a>
                <a></a>
            </div>

            <h1></h1>
        </div>
    </body>
</html>