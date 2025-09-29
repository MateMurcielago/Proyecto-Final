package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.embebidas.ArticuloClienteId;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.intermedias.ReparacionElectronico;
import rdt.arrieta.casa.clases.intermedias.ReparacionLed;
import rdt.arrieta.casa.clases.intermedias.ReparacionPantalla;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verReparacion")
public class VerReparacionServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Session session = DBManager.getSessionFactory().openSession();

        Cliente cliente = session.get(Cliente.class, clienteId);
        modelo_articulo articulo = session.get(modelo_articulo.class, articuloId);

        Reparacion reparacion = session.get(Reparacion.class, reparacionId);
        reparacion.getElectronicos().size();
        reparacion.getPantallas().size();
        reparacion.getLeds().size();
        List<ReparacionElectronico> electronicos = reparacion.getElectronicos();
        List<ReparacionPantalla> pantallas = reparacion.getPantallas();
        List<ReparacionLed> leds = reparacion.getLeds();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);

        request.setAttribute("cliente", cliente);
        request.setAttribute("articulo", articulo);
        request.setAttribute("reparacion", reparacion);
        request.setAttribute("electronicos", electronicos);
        request.setAttribute("pantallas", pantallas);
        request.setAttribute("leds", leds);

        session.close();

        request.getRequestDispatcher("verReparacion.jsp").forward(request, response);
    }
}
