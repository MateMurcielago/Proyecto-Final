<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String tipo = (String) request.getAttribute("tipo");
    String tipoe = "";
    String nombre = "";
    String codigo = "";
    String tamanio = "";

    if(tipo.equals("electronico")) {
        tipoe = (String) request.getAttribute("tipoe");
        nombre = (String) request.getAttribute("nombre");
        codigo = (String) request.getAttribute("codigo");
    } else {
        tamanio = (String) request.getAttribute("tamanio");
    }
%>

<html>
    <head>
        <title>Agregar Unidades de Repuestos</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <%
                if(tipo.equals("electronico")) {
            %>
                    <h1>Unidades de <%=tipoe%> <%=nombre%> <%=codigo%><br>a agregar a la reparación</h1>
            <%
                } else {
            %>
                    <h1>Unidades de LEDs <%=tamanio%><br>a agregar a la reparación</h1>
            <%
                }
            %>

            <form action="editarCantidadRepuesto" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">
                <input type="hidden" name="repuestoId" value="<%=request.getAttribute("repuestoId")%>">
                <input type="hidden" name="tipo" value="<%=request.getAttribute("tipo")%>">

                <div class="form-row">
                    <input type="number" name="cantidad" placeholder="Cantidad...">
                </div>

                <div class="form-row">
                    <input class="form-check-input" type="checkbox" id="inventario" name="inventario" checked>
                    <label class="form-check-label" for="inventario">Restar del inventario</label>
                </div>

                <button type="submit">Agregar</button>
            </form>
        </div>
    </body>
</html>