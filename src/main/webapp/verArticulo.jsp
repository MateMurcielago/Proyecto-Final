<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ArticuloCliente" %>
<%@ page import="rdt.arrieta.casa.clases.Reparacion" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ArticuloCliente articulo = (ArticuloCliente) request.getAttribute("articulo");
    List<Reparacion> reparaciones = (List<Reparacion>) request.getAttribute("reparaciones");
%>

<html>
    <head>
        <title>Ver Artículo</title>
        <link rel="stylesheet" href="css/vista.css">
    </head>
    <body>
        <div class="container">
            <h1><%= articulo.getArticulo().getMarca().getTipo().getTipo() %></h1>

            <p><strong>Marca: </strong><%= articulo.getArticulo().getMarca().getMarca() %> <strong>- Modelo: </strong><%= articulo.getArticulo().getModelo() %></p>
            <p><strong>Dueño: </strong><%= articulo.getCliente().getApellido() %> <%= articulo.getCliente().getNombre() %></p>

            <div class="item-header">
                <h2>Reparaciones</h2>
                <div class="btn-container">
                    <a href="agregarReparacion?clienteId=<%= articulo.getCliente().getId() %>&articuloId=<%= articulo.getArticulo().getId()%>" class="btn-add">+</a>
                </div>
            </div>

            <div class="item-list">
                <%
                    if(reparaciones != null && !reparaciones.isEmpty()) {
                        for(Reparacion r : reparaciones) {
                %>
                            <div class="item-card">
                                <div class="item-info">
                                    <strong><%= r.getEstado() %></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="verReparacion?clienteId=<%=articulo.getCliente().getId()%>&articuloId=<%=articulo.getArticulo().getId()%>&reparacionId=<%=r.getId()%>">Ver Más</a>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <p class="vacio">No hay reparaciones para mostrar</p>
                <%
                    }
                %>
            </div>

            <div class="acciones">
                <div class="btn-container">
                    <form action="eliminar" method="post" style="margin:0;"
                                onsubmit="return confirm('¿Estás seguro de eliminar este artículo?');">
                        <input type="hidden" name="tipo" value="articulo">
                        <input type="hidden" name="idCliente" value="<%=articulo.getCliente().getId()%>">
                        <input type="hidden" name="idArticulo" value="<%=articulo.getArticulo().getId()%>">
                        <button type="submit">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>