<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.tipo_electronico" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Tipo de Repuesto Electrónico</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar tipo de<br>repuesto electrónico</h1>

            <form action="agregarElectronicoTipo" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">

                <div class="form-row">
                    <label for="label-text">Tipo:</label>
                    <select name="tipoId" required>
                        <%
                            List<tipo_electronico> tipos = (List<tipo_electronico>) request.getAttribute("tipos");
                            if(tipos != null) {
                                for(tipo_electronico t : tipos) {
                        %>
                                    <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <button type="submit">Siguiente</button>
            </form>
        </div>
    </body>
</html>