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
        <title>Nuevo Artículo...</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo artículo</h1>
            <h1></h1>
            <div class="row">
                <a href="crearTipoArticulo.jsp">Tipo de Artículo</a>
                <a></a>
            </div>
            <div class="row">
                <a href="crearMarcaArticulo">Marca de Artículo</a>
                <a></a>
            </div>
            <div class="row">
                <a href="seleccionarTipoArticulo">Modelo de Artículo</a>
            </div>
            <h1></h1>
        </div>
    </body>
</html>