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

