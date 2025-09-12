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
        <title>Nuevo Código de Electrónico</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo código<br>de electrónico</h1>

            <form action="crearCodigoElectronico" method="post">
                <div class="form-row">
                    <label for="label-text">Nombre:</label>
                    <select name="nombreId" required>
                        <%
                            List<nombre_electronico> nombres =
                                    (List<nombre_electronico>) request.getAttribute("nombres");
                            if(nombres != null) {
                                for (nombre_electronico  n : nombres) {
                        %>
                                    <option value="<%=n.getId()%>"><%=n.getNombre()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-row">
                    <label for="label-text">Código:</label>
                    <input type="text" id="codigo" name="codigo" required>
                </div>

                <div class="form-row">
                    <label for="label-text">Precio: $</label>
                    <input type="number" id="precio" name="precio" step="0.01" min="0">
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>