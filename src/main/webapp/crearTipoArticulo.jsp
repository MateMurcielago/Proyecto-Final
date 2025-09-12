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
        <title>Nuevo tipo de artículo</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo tipo<br>de artículo</h1>

            <form action="crearTipoArticulo" method="post">
                <div class="form-row">
                    <label for="label-text">Tipo:</label>
                    <input type="text" id="tipo" name="tipo" required>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>