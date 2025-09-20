<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.tamanio_pantalla" %>
<%@ page import="rdt.arrieta.casa.clases.tipo_pantalla" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Nuevo Código de Pantalla</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Nuevo código<br>de pantalla</h1>

            <form action="crearCodigoPantalla" method="post">
                <div class="form-row">
                    <label for="label-text">Tamaño:</label>
                    <select name="tamanioId" required>
                        <%
                            List<tamanio_pantalla> tamanios =
                                    (List<tamanio_pantalla>) request.getAttribute("tamanios");
                            if(tamanios != null) {
                                for (tamanio_pantalla ta : tamanios) {
                        %>
                                    <option value="<%=ta.getId()%>"><%=ta.getTamanio()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="label-text">''</label>
                </div>

                <div class="form-row">
                    <label for="label-text">Tipo:</label>
                    <select name="tipoId" required>
                        <%
                            List<tipo_pantalla> tipos = (List<tipo_pantalla>) request.getAttribute("tipos");
                            if(tipos != null) {
                                for (tipo_pantalla ti : tipos) {
                        %>
                                    <option value="<%=ti.getId()%>"><%=ti.getTipo()%></option>
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