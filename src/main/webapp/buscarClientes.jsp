<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <title>Buscar Clientes</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Clientes</h1>

            <form class="search-box" method="get" action="buscarClientes">
                <input type="text" name="apellido" placeholder="Apellido..." value="${param.apellido}">
                <h1> </h1>
                <input type="text" name="nombre" placeholder="Nombre..." value="${param.nombre}">
                <button type="submit">Buscar</button>
            </form>

            <%
                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                if(clientes != null) {
                    for(Cliente c : clientes) {
            %>
                        <div class="item-card">
                            <div class="item-info">
                                <strong><%=c.getApellido()%> <%=c.getNombre()%></strong>
                            </div>

                            <div class="item-actions">
                                <a href="verCliente?id=<%=c.getId()%>">Ver Más</a>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(clientes.isEmpty()) {
            %>
                    <p class="vacio">No hay clientes para mostrar</p>
            <%
                }
            %>

            <a href="crearCliente.jsp" class="btn-nuevo">Nuevo Cliente...</a>
        </div>
    </body>
</html>