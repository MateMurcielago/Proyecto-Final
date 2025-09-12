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
        <title>Nuevo nombre de electrónico</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
        <h1>Nuevo nombre<br>de electrónico</h1>

        <form action="crearNombreElectronico" method="post">
            <div class="form-row">
                <label for="label-text">Tipo:</label>
                <select name="tipoId" required>
                    <%
                        List<tipo_electronico> tipos = (List<tipo_electronico>) request.getAttribute("tipos");
                        if (tipos != null) {
                            for (tipo_electronico t : tipos) {
                    %>
                                <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                    <%
                            }
                        }
                    %>
                </select><br>
            </div>

            <div class="form-row">
                <label for="label-text">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>

            <button type="submit">Guardar</button>
        </form>
        </div>
    </body>
</html>