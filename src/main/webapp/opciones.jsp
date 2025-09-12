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
        <title>Opciones</title>
        <link rel="stylesheet" href="css/opciones.css">
    </head>
    <body>
        <!-- Menú desplegable -->
        <div class="menu-btn" onclick="toggleMenu()">☰</div>
        <div id="sidebar" class="sidebar">
            <t>Menú</t>
            <a href="index.jsp">Inicio</a>
            <a href="opciones">Opciones</a>
            <t></t>
            <t>Nuevo...</t>
            <a href="crearTipoArticulo.jsp">Tipo de Artículo</a>
        </div>
        <div class="config-container">
            <h1>Opciones</h1>
            <form action="opciones" method="post">
                <!-- Campo de texto -->
                <div class="form-row">
                    <label for="valorHora">Hora de mano de obra: $</label>
                    <input type="number" id="valorHora" name="valorHora" step="0.01" min="0" value="${valorGuardado}">
                </div>

                <!-- Botón deslizante -->
                <div class="form-row">
                    <span class="label-text">Preguntar si avisar al cliente</span>
                    <div class="switch-container">
                        <label class="switch">
                            <input type="checkbox" name="avisar" ${opcionActiva ? "checked" : ""}>
                            <span class="slider"></span>
                        </label>
                    </div>
                </div>

                <button type="submit">Guardar cambios</button>
            </form>
        </div>
        <script>
            function toggleMenu() {
                document.getElementById("sidebar").classList.toggle("active");
            }
        </script>
    </body>
</html>