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
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/editarReparacion")
public class EditarReparacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        Session session = DBManager.getSessionFactory().openSession();

        Reparacion reparacion = session.get(Reparacion.class, reparacionId);

        session.close();

        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacion", reparacion);

        request.getRequestDispatcher("editarReparacion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        String fechaIngresoStr = request.getParameter("fechaIngreso");
        String desperfecto = request.getParameter("desperfecto");
        int horas_mano_obra = Integer.parseInt(request.getParameter("horas_mano_obra"));

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        Reparacion r = session.get(Reparacion.class, reparacionId);

        r.setFecha_ingreso(LocalDate.parse(fechaIngresoStr));
        if(!request.getParameter("fechaFin").equals("-")) {
            String fechaFinStr = request.getParameter("fechaFin");
            r.setFecha_fin(LocalDate.parse(fechaFinStr));
        }
        if(!request.getParameter("fechaRetiro").equals("-")) {
            String fechaRetiroStr = request.getParameter("fechaRetiro");
            r.setFecha_retiro(LocalDate.parse(fechaRetiroStr));
        }
        r.setDesperfecto(desperfecto);
        if(!request.getParameter("trabajo_realizado").equals("-")) {
            String trabajo_realizado = request.getParameter("trabajo_realizado");
            r.setTrabajo_realizado(trabajo_realizado);
        }
        r.setHoras_mano_obra(horas_mano_obra);
        if(!request.getParameter("garantia").equals("-")) {
            String garantia = request.getParameter("garantia");
            r.setGarantia(garantia);
        }

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                "&reparacionId=" + reparacionId);
    }
}
