<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="rdt.arrieta.casa.clases.FallaComun" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    FallaComun falla = (FallaComun) request.getAttribute("falla");
%>

<html>
    <head>
        <title>Detalles de la Falla</title>
        <link rel="stylesheet" href="css/vista.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Detalles de la falla</h1>

            <p><strong>De: </strong><%=falla.getArticulo().getMarca().getTipo().getTipo()%> <%=falla.getArticulo().getMarca().getMarca()%> <%=falla.getArticulo().getModelo()%></p>

            <p><strong>Falla</strong></p>
            <div class="descripcion">
                <p><%=falla.getFalla()%></p>
            </div>

            <p><strong>Solución</strong></p>
            <div class="descripcion">
                <p><%=falla.getSolucion()%></p>
            </div>

            <div class="acciones">
                <div class="btn-container">
                    <form action="editarFallaComun" method="get">
                        <input type="hidden" name="fallaId" value="<%=falla.getId()%>">
                        <button type="submit">Editar...</button>
                    </form>
                </div>

                <div class="btn-container">
                    <form action="eliminar" method="post" style="margin:0;"
                                onsubmit="return confirm('¿Estás seguro de eliminar esta falla?');">
                        <input type="hidden" name="tipo" value="falla">
                        <input type="hidden" name="fallaId" value="<%=falla.getId()%>">
                        <button type="submit"><i class="fa fa-exclamation-circle"></i> Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>