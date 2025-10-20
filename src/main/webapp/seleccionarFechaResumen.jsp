<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Date hoy = new Date();
%>

<html>
    <head>
        <title>Seleccionar Fecha</title>
        <link rel="stylesheet" href="css/creador.css">
    </head>
    <body>
        <div class="container">
            <h1>Seleccionar fecha</h1>

            <form action="seleccionarFechaResumen" method="post">
                <div class="form-row">
                    <label for="label-text">Ver resúmen de </label>
                    <div class="mes-container">
                        <select name="mes" value="<%=hoy.getMonth()%>">
                            <option value="0" <%= hoy.getMonth() == 0 ? "selected" : "" %>>Enero</option>
                            <option value="1" <%= hoy.getMonth() == 1 ? "selected" : "" %>>Febrero</option>
                            <option value="2" <%= hoy.getMonth() == 2 ? "selected" : "" %>>Marzo</option>
                            <option value="3" <%= hoy.getMonth() == 3 ? "selected" : "" %>>Abril</option>
                            <option value="4" <%= hoy.getMonth() == 4 ? "selected" : "" %>>Mayo</option>
                            <option value="5" <%= hoy.getMonth() == 5 ? "selected" : "" %>>Junio</option>
                            <option value="6" <%= hoy.getMonth() == 6 ? "selected" : "" %>>Julio</option>
                            <option value="7" <%= hoy.getMonth() == 7 ? "selected" : "" %>>Agosto</option>
                            <option value="8" <%= hoy.getMonth() == 8 ? "selected" : "" %>>Septiembre</option>
                            <option value="9" <%= hoy.getMonth() == 9 ? "selected" : "" %>>Octubre</option>
                            <option value="10" <%=hoy.getMonth() == 10 ? "selected" : ""%>>Noviembre</option>
                            <option value="11" <%=hoy.getMonth() == 11 ? "selected" : ""%>>Diciembre</option>
                        <select>
                    </div>
                    <label for="label-text">del </label>
                    <div class="anio-container">
                        <select name = "anio" value="<%=hoy.getYear() + 1900%>">
                            <option value="<%=hoy.getYear() + 1900%>" selected><%=hoy.getYear() + 1900%></option>
                            <option value="<%=hoy.getYear() + 1900 - 1%>"><%=hoy.getYear() + 1900 - 1%></option>
                            <option value="<%=hoy.getYear() + 1900 - 2%>"><%=hoy.getYear() + 1900 - 2%></option>
                            <option value="<%=hoy.getYear() + 1900 - 3%>"><%=hoy.getYear() + 1900 - 3%></option>
                            <option value="<%=hoy.getYear() + 1900 - 4%>"><%=hoy.getYear() + 1900 - 4%></option>
                            <option value="<%=hoy.getYear() + 1900 - 5%>"><%=hoy.getYear() + 1900 - 5%></option>
                            <option value="<%=hoy.getYear() + 1900 - 6%>"><%=hoy.getYear() + 1900 - 6%></option>
                            <option value="<%=hoy.getYear() + 1900 - 7%>"><%=hoy.getYear() + 1900 - 7%></option>
                            <option value="<%=hoy.getYear() + 1900 - 8%>"><%=hoy.getYear() + 1900 - 8%></option>
                            <option value="<%=hoy.getYear() + 1900 - 9%>"><%=hoy.getYear() + 1900 - 9%></option>
                            <option value="<%=hoy.getYear() + 1900 - 10%>"><%=hoy.getYear() + 1900 - 10%></option>
                        </select>
                    </div>
                </div>

                <button type="submit">Ver Resúmen</button>
            </form>
        </div>
    </body>
</html>