package rdt.arrieta.casa.opciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Properties;

@WebServlet("/opciones")
public class OpcionesServlet extends HttpServlet {
    private final String CONFIG_FILE = "/WEB-INF/opciones.ini";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Properties props = new Properties();
        String path = getServletContext().getRealPath(CONFIG_FILE);
        try (InputStream input = new FileInputStream(path)) {
            props.load(input);
        } catch (FileNotFoundException e) {
            // archivo aún no existe, se ignora
        }

        String valorStr = props.getProperty("valorHora", "0.0");
        double valor = Double.parseDouble(valorStr);

        boolean avisar = "true".equals(props.getProperty("avisar", "false"));

        request.setAttribute("valorGuardado", valor);
        request.setAttribute("opcionActiva", avisar);

        request.getRequestDispatcher("opciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String texto = request.getParameter("valorHora");
        boolean opcion = request.getParameter("avisar") != null;
        String valorStr = request.getParameter("valorHora");
        double valor = 0.0;

        if (valorStr != null && !valorStr.isEmpty()) {
            try {
                valor = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                valor = 0.0; // valor por defecto si está mal escrito
            }
        }

        Properties props = new Properties();
        props.setProperty("valorHora", Double.toString(valor));
        props.setProperty("avisar", Boolean.toString(opcion));

        String path = getServletContext().getRealPath(CONFIG_FILE);
        try (OutputStream out = new FileOutputStream(path)) {
            props.store(out, "Configuración del sistema");
        }

        response.sendRedirect("opciones"); // recarga la pantalla con datos guardados
    }
}
