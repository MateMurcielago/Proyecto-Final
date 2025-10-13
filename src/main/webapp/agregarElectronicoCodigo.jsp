<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.codigo_electronico" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Código de Repuesto Electrónico</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar código de<br>repuesto electrónico</h1>

            <form action="agregarElectronicoCodigo" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">

                <div class="form-row">
                    <label for="label-text">Tipo: <%=request.getAttribute("tipo")%></label>
                </div>

                <div class="form-row">
                    <label for="label-text">Nombre: <%=request.getAttribute("nombre")%></label>
                </div>

                <div class="form-row">
                    <label for="label-text">Código:</label>
                    <select name="codigoId" required>
                        <%
                            List<codigo_electronico> codigos =
                                    (List<codigo_electronico>) request.getAttribute("codigos");
                            if(codigos != null) {
                                for(codigo_electronico c : codigos) {
                        %>
                                    <option value="<%=c.getId()%>"><%=c.getCodigo()%></option>
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