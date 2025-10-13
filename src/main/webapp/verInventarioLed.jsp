<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Inventario_led" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Inventario de Repuestos de Leds</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Inventario de repuestos de leds</h1>

            <div class="search-container">
                <form method="get" action="verInventarioLed">
                    <div class="search-row">
                        <input type="text" name="tamanio" placeholder="Tamaño..." value="${param.tamanio}">

                        <div class="btn-container">
                            <button type="submit">Buscar</button>
                        </div>
                    </div>

                    <div class="search-row">
                        <label for="label-text">Cantidad: </label>
                        <input type="number" name="cantidad" value="${param.cantidad}">
                    </div>
                </form>
            </div>

            <%
                List<Inventario_led> leds = (List<Inventario_led>) request.getAttribute("leds");
                if(leds != null) {
                    for(Inventario_led l : leds) {
            %>
                        <div class="item-card-2">
                            <div class="card-header">
                                <div class="item-info">
                                    <strong><%=l.getLed().getTamanio()%> $<%=l.getInventario().getPrecio()%></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="editarPrecioInventario?tipo=led&repuestoId=<%=l.getLed().getId()%>&inventarioId=<%=l.getInventario().getId()%>&precio=<%=l.getInventario().getPrecio()%>">Precio...</a>
                                </div>
                            </div>

                            <div class="card-header">
                                <div class="item-info">
                                    <p>Unidades: <%=l.getInventario().getCantidad()%></p>
                                </div>

                                <div class="item-actions">
                                    <a href="editarCantidadInventario?tipo=led&accion=sumar&repuestoId=<%=l.getLed().getId()%>&inventarioId=<%=l.getInventario().getId()%>">+</a>
                                    <a href="editarCantidadInventario?tipo=led&accion=restar&repuestoId=<%=l.getLed().getId()%>&inventarioId=<%=l.getInventario().getId()%>">-</a>
                                </div>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(leds.isEmpty()) {
            %>
                    <p class="vacio">No hay repuestos para mostrar</p>
            <%
                }
            %>
        </div>
    </body>
</html>