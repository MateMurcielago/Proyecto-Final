<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Inventario_pantalla" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Inventario de Repuestos de Pantallas</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Inventario de repuestos de pantallas</h1>

            <div class="search-container">
                <form method="get" action="verInventarioPantalla">
                    <div class="search-row">
                        <input type="text" name="tipo" placeholder="Tipo..." value="${param.tipo}">
                        <input type="text" name="tamanio" placeholder="Tamaño..." value="${param.tamanio}">
                        <input type="text" name="codigo" placeholder="Código..." value="${param.codigo}">
                    </div>

                    <div class="search-row-2">
                        <div class="cant-container">
                            <label for="label-text">Cantidad: </label>
                            <input type="number" name="cantidad" value="${param.cantidad}">
                        </div>

                        <div class="btn-container">
                            <button type="submit">Buscar</button>
                        </div>
                    </div>
                </form>
            </div>

            <%
                List<Inventario_pantalla> pantallas =
                        (List<Inventario_pantalla>) request.getAttribute("pantallas");
                if(pantallas != null) {
                    for(Inventario_pantalla p : pantallas) {
            %>
                        <div class="item-card-2">
                            <div class="card-header">
                                <div class="item-info">
                                    <strong>(<%=p.getPantalla().getTipo().getTipo()%>) <%=p.getPantalla().getTamanio().getTamanio()%> <%=p.getPantalla().getCodigo()%> $<%=p.getInventario().getPrecio()%></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="editarPrecioInventario?tipo=pantalla&repuestoId=<%=p.getPantalla().getId()%>&inventarioId=<%=p.getInventario().getId()%>&precio=<%=p.getInventario().getPrecio()%>">Precio...</a>
                                </div>
                            </div>

                            <div class="card-header">
                                <div class="item-info">
                                    <p>Unidades: <%=p.getInventario().getCantidad()%></p>
                                </div>

                                <div class="item-actions">
                                    <a href="editarCantidadInventario?tipo=pantalla&accion=+&repuestoId=<%=p.getPantalla().getId()%>&inventarioId=<%=p.getInventario().getId()%>">+</a>
                                    <a href="editarCantidadInventario?tipo=pantalla&accion=-&repuestoId=<%=p.getPantalla().getId()%>&inventarioId=<%=p.getInventario().getId()%>">-</a>
                                </div>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(pantallas.isEmpty()) {
            %>
                    <p class="vacio">No hay repuestos para mostrar</p>
            <%
                }
            %>
        </div>
    </body>
</html>