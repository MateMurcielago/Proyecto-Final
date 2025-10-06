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

@WebServlet("/actualizarEstadoReparacionFinalizado")
public class ActualizarEstadoReparacionFinalizadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        Session session = DBManager.getSessionFactory().openSession();

        int horas_mano_obra = session.get(Reparacion.class, reparacionId).getHoras_mano_obra();

        session.close();

        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);

        request.setAttribute("horas", horas_mano_obra);

        request.getRequestDispatcher("actualizarEstadoReparacionFinalizado.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        String fechaFinStr = request.getParameter("fechaFin");
        String trabajo_realizado = request.getParameter("trabajo_realizado");
        int horas_mano_obra = Integer.parseInt(request.getParameter("horas_mano_obra"));
        String garantia = request.getParameter("garantia");

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

        r.setEstado("FINALIZADO");
        r.setFecha_fin(LocalDate.parse(fechaFinStr));
        r.setTrabajo_realizado(trabajo_realizado);
        r.setHoras_mano_obra(horas_mano_obra);
        r.setGarantia(garantia);

        Double costo = Double.parseDouble(valorStr) * r.getHoras_mano_obra();

        if (!r.getElectronicos().isEmpty()) {
            for (int i = 0; i < r.getElectronicos().size(); i++) {
                costo = costo + session.createQuery("select i from inventario_electronico i " +
                        "where i.id_electronico = :id", Inventario_electronico.class).setParameter("id",
                        r.getElectronicos().get(i).getId()).getResultList().getFirst().getInventario()
                        .getPrecio();
            }
        }

        if (!r.getPantallas().isEmpty()) {
            for (int i = 0; i < r.getPantallas().size(); i++) {
                costo = costo + session.createQuery("select i from inventario_pantalla i " +
                        "where i.id_pantalla = :id", Inventario_pantalla.class).setParameter("id",
                        r.getPantallas().get(i).getId()).getResultList().getFirst().getInventario()
                        .getPrecio();
            }
        }

        if (!r.getLeds().isEmpty()) {
            for (int i = 0; i < r.getLeds().size(); i++) {
                costo = costo + session.createQuery("select i from inventario_led i " +
                        "where i.id_led = :id", Inventario_led.class).setParameter("id",
                        r.getLeds().get(i).getId()).getResultList().getFirst().getInventario()
                        .getPrecio();
            }
        }

        r.setCosto(costo);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                "&reparacionId=" + reparacionId);
    }
}
