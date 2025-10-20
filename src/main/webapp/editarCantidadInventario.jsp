<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String accion = (String) request.getAttribute("accion");
    String ventana;
    String titulo;

    if(accion.equals("sumar")) {
        ventana = "Añadir";
        titulo = "añadir";
    } else {
        ventana = "Quitar";
        titulo = "quitar";
    }
%>

<html>
    <head>
        <title><%=ventana%> Repuestos</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Unidades a <%=titulo%></h1>

            <form method="post" action="editarCantidadInventario">
                <input type="hidden" name="tipo" value="${param.tipo}">
                <input type="hidden" name="repuestoId" value="${param.repuestoId}">
                <input type="hidden" name="inventarioId" value="${param.inventarioId}">
                <input type="hidden" name="accion" value="${param.accion}">

                <div class="form-row">
                    <input type="number" name="cantidad" placeholder="Cantidad...">
                </div>

                <div class="form-row">
                    <input class="form-check-input" type="checkbox" id="compra" name="compra" checked>
                    <label class="form-check-label" for="compra">Compra</label>
                </div>

                <button type="submit"><%=ventana%></button>
            </form>
        </div>
    </body>
</html>