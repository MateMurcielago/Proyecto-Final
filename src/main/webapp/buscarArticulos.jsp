<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ArticuloCliente" %>
<%@ page import="rdt.arrieta.casa.clases.modelo_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.marca_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.tipo_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Buscar Artículos</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Artículos</h1>

            <div class="search-container">
                <form method="get" action="buscarArticulos">
                    <div class="search-row">
                        <input type="text" name="tipo" placeholder="Tipo..." value="${param.tipo}">
                        <input type="text" name="marca" placeholder="Marca..." value="${param.marca}">
                        <input type="text" name="modelo" placeholder="Modelo..." value="${param.modelo}">
                    </div>

                    <div class="search-row">
                        <input type="text" name="apellido" placeholder="Apellido..." value="${param.apellido}">
                        <input type="text" name="nombre" placeholder="Nombre..." value="${param.nombre}">
                        <button type="submit">Buscar</button>
                    </div>
            </div>

            <%
                List<ArticuloCliente> articulos = (List<ArticuloCliente>) request.getAttribute("articulos");
                if(articulos != null) {
                    for(ArticuloCliente ac : articulos) {
            %>
                        <div class="item-card-2">
                            <div class="card-header">
                                <div class="item-info">
                                    <strong><%=ac.getArticulo().getMarca().getTipo().getTipo()%> <%=ac.getArticulo().getMarca().getMarca()%> <%=ac.getArticulo().getModelo()%></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="verArticulo?articuloId=<%=ac.getArticulo().getId()%>&clienteId=<%=ac.getCliente().getId()%>">Ver Más</a>
                                </div>
                            </div>

                            <div class="card-body">
                                <div class="item-info">
                                    <p>De: <%=ac.getCliente().getApellido()%> <%=ac.getCliente().getNombre()%></p>
                                </div>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(articulos.isEmpty()) {
            %>
                    <p class="vacio">No hay artículos para mostrar</p>
            <%
                }
            %>
        </div>
    </body>
</html>