<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="rdt.arrieta.casa.clases.FallaComun" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    FallaComun fallaComun = (FallaComun) request.getAttribute("fallaComun");
%>

<html>
    <head>
        <title>Editar Falla Común</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Editar falla común</h1>

            <label for="label-text">Tipo: <%=fallaComun.getArticulo().getMarca().getTipo().getTipo()%> </label>
            <label for="label-text">Marca: <%=fallaComun.getArticulo().getMarca().getMarca()%></label>
            <label for="label-text">Modelo: <%=fallaComun.getArticulo().getModelo()%></label>

            <form action="editarFallaComun" method="post">
                <input type="hidden" name="fallaId" value="<%=request.getAttribute("fallaId")%>">

                <div class="form-row">
                    <input type="text" name="resumen" placeholder="Resúmen de la falla..." value="<%=fallaComun.getResumen()%>">
                </div>

                <div class="form-row">
                    <textarea class="form-control" id="falla" name="falla" rows="5" placeholder="Descripción de la falla..."><%=fallaComun.getFalla()%></textarea>
                </div>

                <div class="form-row">
                    <textarea class="form-control" id="solucion" name="solucion" rows="5" placeholder="Descripción de la solución..."><%=fallaComun.getSolucion()%></textarea>
                </div>

                <button type="submit">Guardar</button>
            </form>
        </div>
    </body>