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
        <title>Actualizar Estado</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Actualizar estado</h1>

            <form action="actualizarEstadoReparacionRetirado" method="post">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">

                <div class="form-row">
                    <label for="label-text">Fecha de retiro: </label>
                    <input type="date" id="fechaRetiro" name="fechaRetiro" value="<%=hoy%>">
                </div>

                <button type="submit">Actualizar</button>
            </form>
        </div>
    </body>
</html>