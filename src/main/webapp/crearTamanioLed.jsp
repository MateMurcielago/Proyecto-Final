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
        <title>Nuevo Tamaño de LED</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo tamaño de LED</h1>

            <form action="crearTamanioLed" method="post">
                <div class="form-row">
                    <label for="label-text">Tamaño:</label>
                    <input type="text" id="tamanio" name="tamanio" required>
                </div>

                <div class="form-row">
                    <label for="label-text">Precio: $</label>
                    <input type="number" id="precio" name="precio" step="0.01" min="0">
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>