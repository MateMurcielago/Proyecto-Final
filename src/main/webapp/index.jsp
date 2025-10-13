<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Página Principal</title>
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <body>
        <!-- Menú desplegable -->
        <div class="menu-btn" onclick="toggleMenu()">☰</div>
        <div id="sidebar" class="sidebar">
            <a href="index.jsp">Inicio</a>
            <a href="opciones">Opciones</a>
            <a href="buscarFallasComunes">Fallas Comúnes</a>
            <t>Ver...</t>
            <a href="buscarClientes">Clientes</a>
            <a href="buscarArticulos">Artículos</a>
            <a href="buscarReparaciones?estado=TODO">Reparaciones</a>
            <a href="menuInventario.jsp">Inventario</a>
            <t>Nuevo...</t>
            <a href="menuArticulo.jsp">Artículo...</a>
            <a href="menuElectronico.jsp">Repuesto Electrónico...</a>
            <a href="menuPantalla.jsp">Repuesto de Pantalla...</a>
            <a href="crearTamanioLed.jsp">Tamaño de LED</a>
        </div>

        <h1>¡Bienvenido a mi intento de aplicación web!</h1>

        <script>
            function toggleMenu() {
                document.getElementById("sidebar").classList.toggle("active");
            }
        </script>
    </body>
</html>