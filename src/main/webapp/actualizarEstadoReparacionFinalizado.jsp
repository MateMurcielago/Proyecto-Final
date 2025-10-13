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

            <form action="actualizarEstadoReparacionFinalizado" method="post">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">

                <div class="form-row">
                    <label for="label-text">Fecha de finalización: </label>
                    <input type="date" id="fechaFin" name="fechaFin" value="<%=hoy%>">
                </div>

                <textarea class="form-control" id="trabajo_realizado" name="trabajo_realizado" rows="5" placeholder="Trabajo realizado..."></textarea>

                <div class="form-row">
                    <input type="number" id="horas_mano_obra" name="horas_mano_obra" placeholder="Mano de obra..." value="<%=request.getAttribute("horas")%>">
                    <p>Hs</p>

                    <input type="text" id="garantia" name="garantia" placeholder="Garantía...">
                </div>

                <button type="submit">Actualizar</button>
            </form>
        </div>
    </body>
</html>