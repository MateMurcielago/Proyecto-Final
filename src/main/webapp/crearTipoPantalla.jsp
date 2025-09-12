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
        <title>Nuevo tipo de pantalla</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo tipo<br>de pantalla</h1>

            <form  action="crearTipoPantalla" method="post">
                <div class="form-row">
                    <label for="label-text">Tipo:</label>
                    <input type="text" id="tipo" name="tipo" required>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>