<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.marca_articulo" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Nuevo Modelo de Artículo</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo modelo<br>de artículo</h1>

            <form action="crearModeloArticulo" method="post">
                <div class="form-row">
                    <label for="label-text">Marca:</label>
                    <select name="marcaId" required>
                        <%
                            List<marca_articulo> marcas = (List<marca_articulo>) request.getAttribute("marcas");
                            if (marcas != null) {
                                for (marca_articulo m : marcas) {
                        %>
                                    <option value="<%=m.getId()%>"><%=m.getMarca()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-row">
                    <label for="label-text">Modelo:</label>
                    <input type="text" id="modelo" name="modelo" required>
                </div>
                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>