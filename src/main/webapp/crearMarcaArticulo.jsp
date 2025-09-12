<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.tipo_articulo" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Nueva Marca de Artículo</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nueva marca<br>de artículo</h1>

            <form action="crearMarcaArticulo" method="post">
                <div class="form-row">
                    <label for="label-text">Tipo:</label>
                    <select name="tipoId" required>
                        <%
                            List<tipo_articulo> tipos = (List<tipo_articulo>) request.getAttribute("tipos");
                            if (tipos != null) {
                                for (tipo_articulo t : tipos) {
                        %>
                                    <option value="<%=t.getId()%>"><%=t.getTipo()%></option>
                        <%
                                }
                            }
                        %>
                    </select><br>
                </div>

                <div class="form-row">
                    <label for="label-text">Marca:</label>
                    <input type="text" id="marca" name="marca" required>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>