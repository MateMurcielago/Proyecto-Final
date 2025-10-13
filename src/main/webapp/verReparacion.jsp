<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="rdt.arrieta.casa.clases.Cliente" %>
<%@ page import="rdt.arrieta.casa.clases.modelo_articulo" %>
<%@ page import="rdt.arrieta.casa.clases.Reparacion" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ReparacionElectronico" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ReparacionPantalla" %>
<%@ page import="rdt.arrieta.casa.clases.intermedias.ReparacionLed" %>

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Cliente cliente = (Cliente) request.getAttribute("cliente");
    modelo_articulo articulo = (modelo_articulo) request.getAttribute("articulo");
    Reparacion reparacion = (Reparacion) request.getAttribute("reparacion");
    List<ReparacionElectronico> electronicos = (List<ReparacionElectronico>) request.getAttribute("electronicos");
    List<ReparacionPantalla> pantallas = (List<ReparacionPantalla>) request.getAttribute("pantallas");
    List<ReparacionLed> leds = (List<ReparacionLed>) request.getAttribute("leds");
%>

<html>
    <head>
        <title>Ver Reparación</title>
        <link rel="stylesheet" href="css/vista.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Reparación <%=reparacion.getCodigo()%></h1>

            <p><%=articulo.getMarca().getTipo().getTipo()%> <%=articulo.getMarca().getMarca()%> <%=articulo.getModelo()%></p>
            <div class="fecha-row">
                <p><strong>De: </strong><%=cliente.getApellido()%> <%=cliente.getNombre()%></p>
                <%
                    if(reparacion.getEstado().equals("FINALIZADO")) {
                %>
                        <div class="btn-container-3">
                            <button type="button" onclick="window.open('https://wa.me/<%=cliente.getNumero_telefono()%>?text=Su <%=articulo.getMarca().getTipo().getTipo()%> está reparado. Puede pasar por el local a retirarlo.', '_blank')"><i class="fa-brands fa-whatsapp" aria-hidden="true"></i></button>
                        </div>
                <%
                    }
                %>
            </div>

            <div class="fecha-row">
                <p><strong>Ingreso: </strong><%=reparacion.getFecha_ingreso().getDayOfMonth()%>/<%=reparacion.getFecha_ingreso().getMonthValue()%>/<%=reparacion.getFecha_ingreso().getYear()%> </p>
                <p><strong>Finalización: </strong></p>
                <%
                    if(reparacion.getFecha_fin().getDayOfMonth() != 22 && reparacion.getFecha_fin().getMonthValue() != 6 &&
                            reparacion.getFecha_fin().getYear() != 1998) {
                %>
                        <p><%=reparacion.getFecha_fin().getDayOfMonth()%>/<%=reparacion.getFecha_fin().getMonthValue()%>/<%=reparacion.getFecha_fin().getYear()%> </p>
                <%
                    } else {
                %>
                        <p>-</p>
                <%
                    }
                %>
                <p><strong>Retiro: </strong></p>
                <%
                    if(reparacion.getFecha_retiro().getDayOfMonth() != 22 && reparacion.getFecha_retiro().getMonthValue() != 6 &&
                            reparacion.getFecha_retiro().getYear() != 1998) {
                %>
                        <p><%=reparacion.getFecha_retiro().getDayOfMonth()%>/<%=reparacion.getFecha_retiro().getMonthValue()%>/<%=reparacion.getFecha_retiro().getYear()%></p>
                <%
                    } else {
                %>
                        <p>-</p>
                <%
                    }
                %>
            </div>

            <p><strong>Código: </strong><%=reparacion.getCodigo()%> <strong>Estado: </strong><%=reparacion.getEstado()%></p>

            <div class="fecha-row">
                <p><strong>Incluye:</strong></p>
                <%
                    if(reparacion.getRemoto() == "SI")  {
                        if(reparacion.getCable() == "SI" || reparacion.getPilas() == "SI" ||
                                reparacion.getOtro() != "") {
                %>
                            <p>remoto,</p>
                <%
                        } else {
                %>
                            <p>remoto</p>
                <%
                        }
                    }
                %>
                <%
                    if(reparacion.getCable().equals("SI")) {
                        if(reparacion.getPilas().equals("SI") || reparacion.getOtro() != "") {
                %>
                            <p>cable de alimentación,</p>
                <%
                        } else {
                %>
                            <p>cable de alimentación</p>
                <%
                        }
                    }

                    if(reparacion.getPilas() == "SI") {
                        if(reparacion.getOtro() != "") {
                %>
                            <p>pilas,</p>
                <%
                        } else {
                %>
                            <p>pilas</p>
                <%
                        }
                    }

                    if(reparacion.getOtro() != "") {
                %>
                        <p><%=reparacion.getOtro().toLowerCase()%></p>
                <%
                    }
                %>
            </div>

            <p><strong>Desperfecto</strong></p>
            <div class="descripcion">
                <p><%=reparacion.getDesperfecto()%></p>
            </div>

            <%
                if(reparacion.getEstado().equals("FINALIZADO")) {
            %>
                    <p><strong>Trabajo realizado</strong></p>
                    <div class="descripcion">
                        <p><%=reparacion.getTrabajo_realizado()%></p>
                    </div>
            <%
                }
            %>

            <div class="item-header">
                <h2>Repuestos electrónicos</h2>
                <div class="btn-container">
                    <a href="agregarElectronicoTipo?clienteId=<%=cliente.getId()%>&articuloId=<%=articulo.getId()%>&reparacionId=<%=reparacion.getId()%>" class="btn-add">+</a>
                </div>
            </div>

            <div class="item-list">
                <%
                    if(electronicos != null && !electronicos.isEmpty()) {
                        for(ReparacionElectronico e : electronicos) {
                %>
                            <div class="item-card">
                                <div class="item-info">
                                    <strong><%=e.getElectronico().getNombre().getTipo().getTipo()%> <%=e.getElectronico().getNombre().getNombre()%> <%=e.getElectronico().getCodigo()%> (<%=e.getCantidad()%>)</strong>
                                </div>

                                <div class="item-actions">
                                    <a href="editarCantidadRepuesto?tipo=electronico&clienteId=<%=cliente.getId()%>&articuloId=<%=articulo.getId()%>&reparacionId=<%=reparacion.getId()%>&repuestoId=<%=e.getElectronico().getId()%>">+</a>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <p class="vacio">No hay repuestos para mostrar</p>
                <%
                    }
                %>
            </div>

            <div class="item-header">
                <h2>Repuestos de pantalla</h2>
                <div class="btn-container">
                    <a href="agregarPantallaTamanio?clienteId=<%=cliente.getId()%>&articuloId=<%=articulo.getId()%>&reparacionId=<%=reparacion.getId()%>" class="btn-add">+</a>
                </div>
            </div>

            <div class="item-list">
                <%
                    if(pantallas != null && !pantallas.isEmpty()) {
                        for(ReparacionPantalla p : pantallas) {
                %>
                            <div class="item-card">
                                <div class="item-info">
                                    <strong>(<%=p.getPantalla().getTipo().getTipo()%>) <%=p.getPantalla().getTamanio().getTamanio()%>'' <%=p.getPantalla().getCodigo()%></strong>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <p class="vacio">No hay repuestos para mostrar</p>
                <%
                    }
                %>
            </div>

            <div class="item-header">
                <h2>Repuestos de LEDs</h2>
                <div class="btn-container">
                    <a href="agregarLed?clienteId=<%=cliente.getId()%>&articuloId=<%=articulo.getId()%>&reparacionId=<%=reparacion.getId()%>" class="btn-add">+</a>
                </div>
            </div>

            <div class="item-list">
                <%
                    if(leds != null  && !leds.isEmpty()) {
                        for(ReparacionLed l : leds) {
                %>
                            <div class="item-card">
                                <div class="item-info">
                                    <strong>Tamaño: <%=l.getLed().getTamanio()%> (<%=l.getCantidad()%>)</strong>
                                </div>

                                <div class="item-actions">
                                    <a href="editarCantidadRepuesto?tipo=led&clienteId=<%=cliente.getId()%>&articuloId=<%=articulo.getId()%>&reparacionId=<%=reparacion.getId()%>&repuestoId=<%=l.getLed().getId()%>">+</a>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <p class="vacio">No hay repuestos para mostrar</p>
                <%
                    }
                %>
            </div>

            <p><strong>Mano de Obra:</strong> <%=reparacion.getHoras_mano_obra()%> Hs - <strong>Costo:</strong> $<%=reparacion.getCosto()%></p>

            <%
                if(reparacion.getEstado().equals("FINALIZADO")) {
            %>
                    <p><strong>Garantía:</strong> <%=reparacion.getGarantia()%></p>
            <%
                }
            %>

            <div class="acciones">
                <div class="btn-container">
                    <form action="editarReparacion" method="get">
                        <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
                        <input type="hidden" name="articuloId" value="<%=articulo.getId()%>">
                        <input type="hidden" name="reparacionId" value="<%=reparacion.getId()%>">
                        <button type="submit">Editar...</button>
                    </form>
                </div>

                <div class="btn-container">
                    <form action="actualizarEstadoReparacion" method="get">
                        <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
                        <input type="hidden" name="articuloId" value="<%=articulo.getId()%>">
                        <input type="hidden" name="reparacionId" value="<%=reparacion.getId()%>">
                        <button type="submit">Actualizar Estado</button>
                    </form>
                </div>

                <div class="btn-container">
                    <form action="calcularCostoReparacion" method="post">
                        <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
                        <input type="hidden" name="articuloId" value="<%=articulo.getId()%>">
                        <input type="hidden" name="reparacionId" value="<%=reparacion.getId()%>">
                        <button type="submit">Calcular Costo</button>
                    </form>
                </div>

                <div class="btn-container">
                    <form action="eliminar" method="post" style="margin:0;"
                                onsubmit="return confirm('¿Estás seguro de eliminar esta reparación?');">
                        <input type="hidden" name="tipo" value="reparacion">
                        <input type="hidden" name="reparacionId" value="<%=reparacion.getId()%>">
                        <button type="submit"><i class="fa fa-exclamation-circle"></i> Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>