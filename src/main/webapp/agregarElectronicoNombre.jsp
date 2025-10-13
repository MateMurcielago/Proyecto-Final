<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.nombre_electronico" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Nombre de Repuesto Electrónico</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar nombe de<br>repuesto electrónico</h1>

            <form action="agregarElectronicoNombre" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">
                <input type="hidden" name="tipoId" value="<%=request.getAttribute("tipoId")%>">

                <div class="form-row">
                    <label for="label-text">Tipo: <%=request.getAttribute("tipo")%></label>
                </div>

                <div class="form-row">
                    <label for="label-text">Nombre:</label>
                    <select name="nombreId" required>
                        <%
                            List<nombre_electronico> nombres =
                                    (List<nombre_electronico>) request.getAttribute("nombres");
                            if(nombres != null) {
                                for(nombre_electronico n : nombres) {
                        %>
                                    <option value="<%=n.getId()%>"><%=n.getNombre()%></option>
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