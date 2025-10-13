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
        <title>Seleccionar Marca de Artículo</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar marca<br>de artículo</h1>

            <label for="label-text">Tipo: <%= request.getAttribute("tipo") %></label>

            <form action="crearFallaComunMarca" method="post">
                <div class="form-row">
                    <input type="hidden" name="tipoId" value="<%= request.getAttribute("tipoId") %>">

                    <label for="label-text">Marca:</label>
                    <select name="marcaId" required>
                        <%
                            List<marca_articulo> marcas = (List<marca_articulo>) request.getAttribute("marcas");
                            if(marcas != null) {
                                for (marca_articulo m : marcas) {
                        %>
                                    <option value="<%=m.getId()%>"><%=m.getMarca()%></option>
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