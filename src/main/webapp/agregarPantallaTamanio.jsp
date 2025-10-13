<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.tamanio_pantalla" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Seleccionar Tipo de Repuesto de Pantalla</title>
        <link rel="stylesheet" href="css/crearCategoria.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar tamaño de<br>repuesto de pantalla</h1>

            <form action="agregarPantallaTamanio" method="post">
                <input type="hidden" name="clienteId" value="<%=request.getAttribute("clienteId")%>">
                <input type="hidden" name="articuloId" value="<%=request.getAttribute("articuloId")%>">
                <input type="hidden" name="reparacionId" value="<%=request.getAttribute("reparacionId")%>">

                <div class="form-row">
                    <label for="label-text">Tamaño:</label>
                    <select name="tamanioId" required>
                        <%
                            List<tamanio_pantalla> tamanios =
                                    (List<tamanio_pantalla>) request.getAttribute("tamanios");
                            if(tamanios != null) {
                                for(tamanio_pantalla t : tamanios) {
                        %>
                                    <option value="<%=t.getId()%>"><%=t.getTamanio()%>''</option>
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