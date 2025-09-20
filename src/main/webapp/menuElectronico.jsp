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
        <title>Nuevo Repuesto Electrónico</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo repuesto<br>electrónico</h1>
            <h1></h1>
            <div class="row">
                <a href="crearTipoElectronico.jsp">Tipo de Repuesto Electrónico</a>
                <a></a>
            </div>
            <div class="row">
                <a href="crearNombreElectronico">Nombre de Repuesto Electrónico</a>
                <a></a>
            </div>
            <div class="row">
                <a href="seleccionarTipoElectronico">Código de Repuesto Electrónico</a>
            </div>
            <h1></h1>
        </div>
    </body>
</html>