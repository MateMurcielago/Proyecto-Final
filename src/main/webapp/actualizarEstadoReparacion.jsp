<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String estado = (String) request.getAttribute("estado");
    String codigo = (String) request.getAttribute("codigo");
%>

<html>
    <head>
        <title>Actualizar Estado</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Actualizar estado</h1>

            <form action="actualizarEstadoReparacion" method="post">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">

                <input type="hidden" name="estado" value="<%=estado%>">

                <%
                    if(estado.equals("ENTREGADO") || estado.equals("NO REPARADO")) {
                %>
                        <p>No se puede actualizar el estado</p>
                        <input type="hidden" namee="estadoNuevo" value="-">
                <%
                    } else {
                %>
                        <p>Actualizar estado de la reparación: <%=codigo%></p>

                        <div class="form-row">
                            <p>De: <%=estado%> a:</p>

                            <%
                                if(estado.equals("INGRESADO")) {
                            %>
                                    <select name="estadoNuevo">
                                        <option value="EN REPARACIÓN">EN REPARACIÓN</option>
                                        <option value="NO REPARADO">NO REPARADO</option>
                                    </select>
                            <%
                                } else if(estado.equals("EN REPARACIÓN")) {
                            %>
                                    <select name="estadoNuevo">
                                        <option value="FINALIZADO">FINALIZADO</option>
                                        <option value="NO REPARADO">NO REPARADO</option>
                                    </select>
                            <%
                                } else if(estado.equals("FINALIZADO")) {
                            %>
                                    <p>ENTREGADO</p>
                                    <input type="hidden" name="estadoNuevo" value="ENTREGADO">
                            <%
                                }
                            %>
                        </div>
                <%
                    }
                %>

                <button type="submit">Actualizar</button>
            </form>
        </div>
    </body>
</html>