<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.ResumenMesReparacion" %>
<%@ page import="rdt.arrieta.casa.clases.ResumenMesRepuesto" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String mes = (String) request.getAttribute("mes");
    int anio = (int) request.getAttribute("anio");
    List<ResumenMesReparacion> resumenesReparaciones =
            (List<ResumenMesReparacion>) request.getAttribute("resumenesReparaciones");
    List<ResumenMesRepuesto> resumenesRepuestos =
            (List<ResumenMesRepuesto>) request.getAttribute("resumenesRepuestos");
    Double total = (Double) request.getAttribute("total");
%>

<html>
    <head>
        <title>Ver Resúmen</title>
        <link rel="stylesheet" href="css/creador.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Resúmen de <%=mes%> del <%=anio%></h1>

            <p><strong>Reparaciones cobradas:</strong></p>
            <%
                if(resumenesReparaciones != null) {
                    for(ResumenMesReparacion r : resumenesReparaciones) {
            %>
                        <p>     <%=r.getDetalle().getDescripcion()%>: $<%=r.getDetalle().getPrecio()%></p>
            <%
                    }
                }
            %>

            <p><br><strong>Repuestos comprados:</strong></p>
            <%
                if(resumenesRepuestos != null) {
                    for(ResumenMesRepuesto r : resumenesRepuestos) {
            %>
                        <p>     <%=r.getDetalle().getDescripcion()%>: $<%=r.getDetalle().getPrecio()%></p>
            <%
                    }
                }
            %>

            <p><strong>Total: </strong>$<%=total%></p>

            <form action="descargarResumen" method="get">
                <input type="hidden" name="anio" value="<%=anio%>">
                <input type="hidden" name="mes" value="<%=mes%>">
                <input type="hidden" name="total" value="<%=total%>">

                <div class="btn-container">
                    <button type="submit"><i class="fa-solid fa-file-arrow-down"></i> Descargar PDF</button>
                </div>
            </form>
        </div>
    </body>
</html>