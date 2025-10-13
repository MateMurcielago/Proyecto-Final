<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Inventario_electronico" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Inventario de Repuestos Electrónicos</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Inventario de repuestos electrónicos</h1>

            <div class="search-container">
                <form method="get" action="verInventarioElectronico">
                    <div class="search-row">
                        <input type="text" name="tipo" placeholder="Tipo..." value="${param.tipo}">
                        <input type="text" name="nombre" placeholder="Nombre..." value="${param.nombre}">
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
                List<Inventario_electronico> electronicos =
                        (List<Inventario_electronico>) request.getAttribute("electronicos");
                if(electronicos != null) {
                    for(Inventario_electronico e : electronicos) {
            %>
                        <div class="item-card-2">
                            <div class="card-header">
                                    <div class="item-info">
                                        <strong><%=e.getElectronico().getNombre().getTipo().getTipo()%> <%=e.getElectronico().getNombre().getNombre()%> <%=e.getElectronico().getCodigo()%> $<%=e.getInventario().getPrecio()%></strong>
                                    </div>

                                    <div class="item-actions">
                                        <a href="editarPrecioInventario?tipo=electronico&repuestoId=<%=e.getElectronico().getId()%>&inventarioId=<%=e.getInventario().getId()%>&precio=<%=e.getInventario().getPrecio()%>">Precio...</a>
                                    </div>
                            </div>

                            <div class="card-header">
                                <div class="item-info">
                                    <p>Unidades: <%=e.getInventario().getCantidad()%></p>
                                </div>

                                <div class="item-actions">
                                    <a href="editarCantidadInventario?tipo=electronico&accion=sumar&repuestoId=<%=e.getElectronico().getId()%>&inventarioId=<%=e.getInventario().getId()%>">+</a>
                                    <a href="editarCantidadInventario?tipo=electronico&accion=restar&repuestoId=<%=e.getElectronico().getId()%>&inventarioId=<%=e.getInventario().getId()%>">-</a>
                                </div>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(electronicos.isEmpty()) {
            %>
                    <p class="vacio">No hay repuestos para mostrar</p>
            <%
                }
            %>
        </div>
    </body>
</html>