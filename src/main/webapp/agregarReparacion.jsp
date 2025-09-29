<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String hoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>

<html>
    <head>
        <title>Nueva Reparación</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Nueva reparación</h1>

            <form action="agregarReparacion" method="post">
                <div class="form-row">
                    <label for="label-text">Fecha de ingreso: </label>
                    <input type="date" id="fechaIngreso" name="fechaIngreso" value="<%= hoy %>">
                </div>

                <textarea class="form-control" id="desperfecto" name="desperfecto" rows="5" placeholder="Desperfecto..."></textarea>

                <div class="form-row">
                    <input class="form-check-input" type="checkbox" id="remoto" name="remoto">
                    <label class="form-check-label" for="remoto">Remoto</label>

                    <input class="form-check-input" type="checkbox" id="cable" name="cable">
                    <label class="form-check-label" for="cable">Cable de alimentación</label>

                    <input class="form-check-input" type="checkbox" id="pilas" name="pilas">
                    <label class="form-check-label" for="pilas">Pilas</label>

                    <input class="form-check-input" type="checkbox" id="otroBool" name="otroBool">
                    <label class="form-check-label" for="otroBool">otro</label>
                </div>

                <div class="form-row">
                    <input type="text" id="otro" name="otro" placeholder="Otro..." required>
                </div>

                <input type="hidden" name="clienteId" value="<%= request.getAttribute("clienteId") %>">
                <input type="hidden" name="articuloId" value="<%= request.getAttribute("articuloId") %>">

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>