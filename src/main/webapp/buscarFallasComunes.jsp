<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="rdt.arrieta.casa.clases.FallaComun" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<FallaComun> fallas = (List<FallaComun>) request.getAttribute("fallas");
%>

<html>
    <head>
        <title>Buscar Fallas Comúnes</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Fallas Comúnes</h1>

            <div class="search-container">
                <form method="get" action="buscarFallasComunes">
                    <div class="search-row">
                        <input type="text" name="tipo" placeholder="Tipo..." value="${param.tipo}">
                        <input type="text" name="marca" placeholder="Marca..." value="${param.marca}">
                        <input type="text" name="modelo" placeholder="Modelo..." value="${param.modelo}">
                    </div>

                    <div class="search-row-3">
                        <button type="submit">Buscar</button>
                    </div>
                </form>
            </div>

            <%
                if(fallas != null) {
                    for(FallaComun f : fallas) {
            %>
                        <div class="item-card-2">
                            <div class="card-header-2">
                                <div class="item-info">
                                    <strong><%=f.getArticulo().getMarca().getTipo().getTipo()%> <%=f.getArticulo().getMarca().getMarca()%> <%=f.getArticulo().getModelo()%></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="verFallaComun?fallaId=<%=f.getId()%>">Ver Más</a>
                                </div>
                            </div>

                            <div class="item-body-2">
                                <p><%=f.getResumen()%></p>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(fallas.isEmpty()) {
            %>
                    <p class="vacio">No hay fallas para mostrar</p>
            <%
                }
            %>

            <a href="crearFallaComunTipo.jsp" class="btn-nuevo">Nueva Falla...</a>
        </div>
    </body>
</html>