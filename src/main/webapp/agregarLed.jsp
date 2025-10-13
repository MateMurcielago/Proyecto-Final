<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.tamanio_led" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Tamaño de Repuesto de LED</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar tamaño de<br>repuesto de LED</h1>

            <form action="agregarLed" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">

                <div class="form-row">
                    <label for="label-text">Tamaño:</label>
                    <select name="tamanioId" required>
                        <%
                            List<tamanio_led> tamanios = (List<tamanio_led>) request.getAttribute("tamanios");
                            if(tamanios != null) {
                                for(tamanio_led t : tamanios) {
                        %>
                                    <option value="<%=t.getId()%>"><%=t.getTamanio()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-row">
                    <input type="number" name="cantidad" placeholder="Cantidad...">
                </div>

                <div class="form-row">
                    <input class="form-check-input" type="checkbox" id="inventario" name="inventario" checked>
                    <label class="form-check-label" for="inventario">Restar del inventario</label>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>