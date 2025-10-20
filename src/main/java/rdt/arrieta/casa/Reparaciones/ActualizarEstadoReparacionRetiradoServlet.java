package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.ResumenMes;
import rdt.arrieta.casa.clases.ResumenMesReparacion;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/actualizarEstadoReparacionRetirado")
public class ActualizarEstadoReparacionRetiradoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);

        request.getRequestDispatcher("actualizarEstadoReparacionRetirado.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        String fechaRetiroStr = request.getParameter("fechaRetiro");

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        Reparacion r = session.get(Reparacion.class, reparacionId);

        r.setEstado("RETIRADO");
        r.setFecha_fin(LocalDate.parse(fechaRetiroStr));

        ResumenMes resumen = new ResumenMes();
        resumen.setAnio(LocalDate.parse(fechaRetiroStr).getYear());
        resumen.setMes(LocalDate.parse(fechaRetiroStr).getMonthValue());
        resumen.setDescripcion(r.getArticuloCliente().getArticulo().getMarca().getTipo().getTipo() + " " +
                r.getArticuloCliente().getArticulo().getMarca().getMarca() + " " +
                r.getArticuloCliente().getArticulo().getModelo() + " de " +
                r.getArticuloCliente().getCliente().getApellido() + " " +
                r.getArticuloCliente().getCliente().getNombre());
        resumen.setPrecio(r.getCosto());
        session.persist(resumen);

        ResumenMesReparacion resumenR = new ResumenMesReparacion();
        resumenR.setDetalle(resumen);
        session.persist(resumenR);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                "&reparacionId=" + reparacionId);
    }
}
