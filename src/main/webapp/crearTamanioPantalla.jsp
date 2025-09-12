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
        <title>Nuevo tamaño de pantalla</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo tamaño<br>de pantalla</h1>

            <form  action="crearTamanioPantalla" method="post">
                <div class="form-row">
                    <label for="label-text">Tamaño:</label>
                    <input type="text" id="tamanio" name="tamanio" required>
                    <label for="label-text">''</label>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>