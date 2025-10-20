package rdt.arrieta.casa.Resumenes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/seleccionarFechaResumen")
public class SeleccionarFechaResumenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mes = Integer.parseInt(request.getParameter("mes"));
        int anio = Integer.parseInt(request.getParameter("anio"));

        response.sendRedirect(request.getContextPath() + "/verResumen?mes=" + mes + "&anio=" + anio);
    }
}
