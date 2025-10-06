<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="rdt.arrieta.casa.clases.Reparacion" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Reparacion reparacion = (Reparacion) request.getAttribute("reparacion");
%>

<html>
    <head>
        <title>Editar Reparación</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Editar reparación</h1>

            <form action="editarReparacion" method="post">
                <input type="hidden" name="reparacionId" value="<%= reparacion.getId() %>">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">

                <div class="form-row">
                    <label for="label-text">Fecha de ingreso: </label>
                    <input type="date" id="fechaIngreso" name="fechaIngreso" value="<%=reparacion.getFecha_ingreso()%>">
                </div>

                <div class="form-row">
                    <%
                        if(!reparacion.getEstado().equals("INGRESADO") &&
                                !reparacion.getEstado().equals("EN REPARACION") &&
                                !reparacion.getEstado().equals("NO REPARADO")) {
                    %>
                            <label for="label-text">Fecha de finalización: </label>
                            <input type="date" id="fechaFin" name="fechaFin" value="<%=reparacion.getFecha_fin()%>">
                    <%
                        } else {
                    %>
                            <input type="hidden" name="fechaFin" value="-">
                    <%
                        }

                        if(reparacion.getEstado().equals("ENTREGADO")) {
                    %>
                            <label for="label-text">Fecha de retiro: </label>
                            <input type="date" id="fechaRetiro" name="fechaRetiro" value="<%=reparacion.getFecha_retiro()%>">
                    <%
                        } else {
                    %>
                            <input type="hidden" name="fechaRetiro" value="-">
                    <%
                        }
                    %>
                </div>

                <textarea class="form-control" id="desperfecto" name="desperfecto" rows="5" placeholder="Desperfecto..."><%=reparacion.getDesperfecto()%></textarea>

                <%
                    if(reparacion.getEstado().equals("FINALIZADO") || reparacion.getEstado().equals("ENTREGADO")) {
                %>
                        <textarea class="form-control" id="trabajo_realizado" name="trabajo_realizado" rows="5" placeholder="Trabajo realizado..."><%=reparacion.getTrabajo_realizado()%></textarea>
                <%
                    } else {
                %>
                        <input type="hidden" name="trabajo_realizado" value="-">
                <%
                    }
                %>

                <div class="form-row">
                    <input type="number" id="horas_mano_obra" name="horas_mano_obra" placeholder="Mano de obra..." value="<%=reparacion.getHoras_mano_obra()%>">
                    <p>Hs</p>

                    <%
                        if(reparacion.getEstado().equals("FINALIZADO") ||
                                reparacion.getEstado().equals("ENTREGADO")) {
                    %>
                            <input type="text" id="garantia" name="garantia" placeholder="Garantía..." value="<%=reparacion.getGarantia()%>">
                    <%
                        } else {
                    %>
                            <input type="hidden" name="garantia" value="-">
                    <%
                        }
                    %>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>