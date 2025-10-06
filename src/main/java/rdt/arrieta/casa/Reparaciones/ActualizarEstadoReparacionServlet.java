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

@WebServlet("/actualizarEstadoReparacion")
public class ActualizarEstadoReparacionServlet extends HttpServlet {
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
        request.setAttribute("estado", reparacion.getEstado());
        request.setAttribute("codigo", reparacion.getCodigo());

        request.getRequestDispatcher("actualizarEstadoReparacion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        String estado = request.getParameter("estado");
        String estadoNuevo = request.getParameter("estadoNuevo");

        if(estado.equals("ENTREGADO") || estado.equals("NO REPARADO")) {
            response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                    "&reparacionId=" + reparacionId);
        } else {
            if(estadoNuevo.equals("EN REPARACIÓN")) {
                Session session = DBManager.getSessionFactory().openSession();
                session.beginTransaction();

                Reparacion r = session.get(Reparacion.class, reparacionId);

                r.setEstado(estadoNuevo);

                session.getTransaction().commit();
                session.close();

                response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                        "&reparacionId=" + reparacionId);
            } else if(estadoNuevo.equals("FINALIZADO")) {
                response.sendRedirect("actualizarEstadoReparacionFinalizado?clienteId=" + clienteId
                        + "&articuloId=" + articuloId + "&reparacionId=" + reparacionId);
            } else if(estadoNuevo.equals("ENTREGADO")) {
                response.sendRedirect("actualizarEstadoReparacionEntregado?clienteId=" + clienteId
                        + "&articuloId=" + articuloId + "&reparacionId=" + reparacionId);
            } else if(estadoNuevo.equals("NO REPARADO")) {
                Session session = DBManager.getSessionFactory().openSession();
                session.beginTransaction();

                Reparacion r = session.get(Reparacion.class, reparacionId);

                r.setEstado(estadoNuevo);

                session.getTransaction().commit();
                session.close();

                response.sendRedirect("verReparacion?clienteId=" + clienteId + "&articuloId=" + articuloId +
                        "&reparacionId=" + reparacionId);
            }
        }
    }
}
