<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ArticuloCliente" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<ArticuloCliente> articulos = (List<ArticuloCliente>) request.getAttribute("articulos");

    Long car = cliente.getNumero_telefono() / 1000000;
    Long num = cliente.getNumero_telefono() - (car * 1000000);
%>

<html>
    <head>
        <title>Ver Cliente</title>
        <link rel="stylesheet" href="css/vista.css">
    </head>
    <body>
        <div class="container">
            <h1><%= cliente.getApellido() %> <%= cliente.getNombre() %></h1>

            <p><strong>Número de teléfono:</strong> <%= car %>-<%= num %></h1>
            <p><strong>Dirección:</strong> <%= cliente.getCalle() %> <%= cliente.getNumero_casa() %></h1>

            <div class="item-header">
                <h2>Artículos </h2>
                <div class="btn-conatiner">
                    <a href="agregarArticuloTipo?idCliente=<%= cliente.getId() %>" class="btn-add">+</a>
                </div>
            </div>

            <div class="item-list">
                <%
                    if(articulos != null && !articulos.isEmpty()) {
                        for(ArticuloCliente ac : articulos) {
                %>
                            <div class="item-card">
                                <div class="item-info">
                                    <strong><%=ac.getArticulo().getMarca().getTipo().getTipo()%> <%=ac.getArticulo().getMarca().getMarca()%> <%=ac.getArticulo().getModelo()%> </strong>
                                </div>

                                <div class="item-actions">
                                    <a href="verArticulo?clienteId=<%=cliente.getId()%>&articuloId=<%=ac.getArticulo().getId()%>">Ver Más</a>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                    <p class="vacio">No hay artículos para mostrar</p>
                <%
                    }
                %>
            </div>

            <div class="acciones">
                <div class="btn-container">
                    <form action="editarCliente" method="get" style="margin:0;">
                        <input type="hidden" name="idCliente" value="<%= cliente.getId() %>">
                        <button type="submit">Editar...</button>
                    </form>
                </div>
                <div class="btn-container">
                    <form action="eliminar" method="post" style="margin:0;"
                                        onsubmit="return confirm('¿Estás seguro de eliminar este cliente?');">
                        <input type="hidden" name="tipo" value="cliente">
                        <input type="hidden" name="idCliente" value="<%= cliente.getId() %>">
                        <button type="submit">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>