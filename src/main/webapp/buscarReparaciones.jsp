<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@ page import="rdt.arrieta.casa.clases.intermedias.ArticuloCliente" %>
<%@ page import="rdt.arrieta.casa.clases.modelo_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.marca_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.tipo_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>
<%@ page import="rdt.arrieta.casa.clases.Reparacion" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String estado = (String) request.getAttribute("estado");
    List<Reparacion> reparaciones = (List<Reparacion>) request.getAttribute("reparaciones");
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<html>
    <head>
        <title>Buscar Reparaciones</title>
        <link rel="stylesheet" href="css/buscador.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Artículos</h1>

            <div class="search-container">
                <form method="get" action="buscarReparaciones">
                    <div class="search-row">
                        <label for="label-text">Estado:</label>
                        <select name="estado" value="${param.estado}">
                            <option value="TODO" <%= "TODO".equals(estado) ? "selected" : ""%>>TODO</option>
                            <option value="INGRESADO" <%= "INGRESADO".equals(estado) ? "selected" : ""%>>INGRESADO</option>
                            <option value="EN REPARACIÓN" <%= "EN REPARACIÓN".equals(estado) ? "selected" : ""%>>EN REPARACIÓN</option>
                            <option value="FINALIZADO" <%= "FINALIZADO".equals(estado) ? "selected" : ""%>>FINALIZADO</option>
                            <option value="ENTREGADO" <%= "ENTREGADO".equals(estado) ? "selected" : ""%>>ENTREGADO</option>
                            <option value="NO REPARADO" <%= "NO REPARADO".equals(estado) ? "selected" : ""%>>NO REPARADO</option>
                        </select>
                    </div>

                    <div class="search-row">
                        <input type="text" name="tipo" placeholder="Tipo..." value="${param.tipo}">
                        <input type="text" name="marca" placeholder="Marca..." value="${param.marca}">
                        <input type="text" name="modelo" placeholder="Modelo..." value="${param.modelo}">
                    </div>

                    <div class="search-row">
                        <input type="text" name="apellido" placeholder="Apellido..." value="${param.apellido}">
                        <input type="text" name="nombre" placeholder="Nombre..." value="${param.nombre}">
                        <button type="submit">Buscar</button>
                    </div>
                </form>

                <form method="get" action="buscarReparacionCodigo">
                    <button type="submit">Buscar por códgio...</button>
                </form>
            </div>

            <%
                if(reparaciones != null) {
                    for(Reparacion r : reparaciones) {
            %>
                        <div class="item-card-2">
                            <div class="card-header-2">
                                <div class="item-info">
                                    <strong><%=r.getFecha_ingreso().format(formato)%> <%=r.getCodigo()%> <%=r.getEstado()%></strong>
                                </div>

                                <div class="item-actions">
                                    <a href="verReparacion?clienteId=<%=r.getArticuloCliente().getCliente().getId()%>&articuloId=<%=r.getArticuloCliente().getArticulo().getId()%>&reparacionId=<%=r.getId()%>">Ver Más</a>
                                </div>
                            </div>

                            <div class="card-body-2">
                                <div class="item-info">
                                    <strong><%=r.getArticuloCliente().getArticulo().getMarca().getTipo().getTipo()%> <%=r.getArticuloCliente().getArticulo().getMarca().getMarca()%> <%=r.getArticuloCliente().getArticulo().getModelo()%></strong>
                                </div>
                            </div>

                            <div class="card-body-2">
                                <div class="item-info">
                                    <strong>De: <%=r.getArticuloCliente().getCliente().getApellido()%> <%=r.getArticuloCliente().getCliente().getNombre()%></strong>
                                </div>
                            </div>
                        </div>
            <%
                    }
                }
            %>

            <%
                if(reparaciones.isEmpty()) {
            %>
                    <p class="vacio">No hay reparaciones para mostrar</p>
            <%
                }
            %>
        </div>
    </body>
<html>