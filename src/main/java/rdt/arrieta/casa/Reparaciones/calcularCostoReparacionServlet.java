package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.Inventario_electronico;
import rdt.arrieta.casa.clases.Inventario_pantalla;
import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/calcularCostoReparacion")
public class calcularCostoReparacionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        Reparacion r = session.get(Reparacion.class, reparacionId);

        String CONFIG_FILE = "/WEB-INF/opciones.ini";
        Properties props = new Properties();
        String path = getServletContext().getRealPath(CONFIG_FILE);
        try (InputStream input = new FileInputStream(path)) {
            props.load(input);
        } catch (FileNotFoundException e) {
            // archivo aún no existe, se ignora
        }

        String valorStr = props.getProperty("valorHora", "0.0");

        Double costo = Double.parseDouble(valorStr) * r.getHoras_mano_obra();

        if (!r.getElectronicos().isEmpty()) {
            for (int i = 0; i < r.getElectronicos().size(); i++) {
                costo = costo + (session.createQuery("select i from Inventario_electronico i " +
                        "where i.electronico.id = :id", Inventario_electronico.class).setParameter("id",
                        r.getElectronicos().get(i).getElectronico().getId()).getResultList().getFirst()
                        .getInventario().getPrecio() * r.getElectronicos().get(i).getCantidad());
            }
        }

        if (!r.getPantallas().isEmpty()) {
            for (int i = 0; i < r.getPantallas().size(); i++) {
                costo = costo + session.createQuery("select i from Inventario_pantalla i " +
                        "where i.pantalla.id = :id", Inventario_pantalla.class).setParameter("id",
                        r.getPantallas().get(i).getPantalla().getId()).getResultList().getFirst().getInventario()
                        .getPrecio();
            }
        }

        if (!r.getLeds().isEmpty()) {
            for (int i = 0; i < r.getLeds().size(); i++) {
                costo = costo + (session.createQuery("select i from Inventario_led i " +
                        "where i.led.id = :id", Inventario_led.class).setParameter("id",
                        r.getLeds().get(i).getLed().getId()).getResultList().getFirst().getInventario()
                        .getPrecio() * r.getLeds().get(i).getCantidad());
            }
        }

        r.setCosto(costo);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                "&reparacionId=" + reparacionId);
    }
}
