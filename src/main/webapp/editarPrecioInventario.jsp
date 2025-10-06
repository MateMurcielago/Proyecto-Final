<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Editar Precio</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Editar Precio</h1>

            <form method="post" action="editarPrecioInventario">
                <input type="hidden" name="tipo" value="${param.tipo}">
                <input type="hidden" name="repuestoId" value="${param.repuestoId}">
                <input type="hidden" name="inventarioId" value="${param.inventarioId}">

                <div class="seach-row">
                    <input type="number" name="precio" placeholder="Precio..." value="${param.precio}">
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </div>
</html>