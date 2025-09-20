<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>

<html>
    <head>
        <title>Editar Cliente</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Editar cliente</h1>

            <form action="editarCliente" method="post">
                <input type="hidden" name="idCliente" value="<%= cliente.getId() %>">

                <div class="form-row">
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido..." value="<%= cliente.getApellido() %>" required>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre(s)..." value="<%= cliente.getNombre() %>" required>
                </div>

                <div class="form-row">
                    <input type="text" id="calle" name="calle" placeholder="Calle..." value="<%= cliente.getCalle() %>" required>
                    <input type="text" id="numero_casa" name="numero_casa" placeholder="N°..." value="<%= cliente.getNumero_casa() %>" required>
                </div>

                <div class="form-row">
                    <input type="number" id="numero_telefono" name="numero_telefono" placeholder="Teléfono..."value="<%= cliente.getNumero_telefono() %>"  required>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>
</html>