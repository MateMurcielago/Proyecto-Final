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
        <title>Nuevo Cliente</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo cliente</h1>

            <form action="crearCliente" method="post">
                <div class="form-row">
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido..." required>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre(s)..." required>
                </div>

                <div class="form-row">
                    <input type="text" id="calle" name="calle" placeholder="Calle..." required>
                    <input type="text" id="numero_casa" name="numero_casa" placeholder="N°..." required>
                </div>

                <div class="form-row">
                    <input type="number" id="numero_telefono" name="numero_telefono" placeholder="Teléfono..." required>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>