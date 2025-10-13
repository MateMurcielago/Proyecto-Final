<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.modelo_articulo" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Modelo de Artículo</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar modelo<br>de artículo</h1>

            <label for="label-text">Tipo: <%= request.getAttribute("tipo") %> </label>
            <label for="label-text">Marca: <%= request.getAttribute("marca") %></label>

            <form action="agregarArticuloModelo" method="post">
                <div class="form-row">
                    <label for="label-text">Marca:</label>
                    <select name="modeloId" required>
                        <%
                            List<modelo_articulo> modelos = (List<modelo_articulo>) request.getAttribute("modelos");
                            if(modelos != null) {
                                for (modelo_articulo m : modelos) {
                        %>
                                    <option value="<%=m.getId()%>"><%=m.getModelo()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-row">
                    <input type="text" name="resumen" placeholder="Resúmen de la falla...">
                </div>

                <div class="form-row">
                    <textarea class="form-control" id="falla" name="falla" rows="5" placeholder="Descripción de la falla..."></textarea>
                </div>

                <div class="form-row">
                    <textarea class="form-control" id="solucion" name="solucion" rows="5" placeholder="Descripción de la solución..."></textarea>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>