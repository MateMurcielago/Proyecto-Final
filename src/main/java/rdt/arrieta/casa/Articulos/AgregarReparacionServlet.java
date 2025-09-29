package rdt.arrieta.casa.Articulos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.embebidas.ArticuloClienteId;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarReparacion")
public class AgregarReparacionServlet extends HttpServlet {
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

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);

        request.getRequestDispatcher("agregarReparacion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));

        ArticuloClienteId id = new ArticuloClienteId(clienteId, articuloId);

        String fechaStr = request.getParameter("fechaIngreso");
        String desperfecto = request.getParameter("desperfecto");
        String remoto = request.getParameter("remoto");
        String cable = request.getParameter("cable");
        String pilas = request.getParameter("pilas");
        String otroBool = request.getParameter("otroBool");
        String otro = request.getParameter("otro");

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener el artículo
            ArticuloCliente articulo = session.get(ArticuloCliente.class, id);

            Reparacion r = new Reparacion();
            r.setArticuloCliente(articulo);
            r.setFecha_ingreso(LocalDate.parse(fechaStr));
            int i = r.getFecha_ingreso().getYear() - 2000;
            r.setCodigo("REP" + i + r.getId());
            r.setDesperfecto(desperfecto);
            if(remoto != null) {
                r.setRemoto("SI");
            } else {
                r.setRemoto("NO");
            }
            if(cable != null) {
                r.setCable("SI");
            } else {
                r.setCable("NO");
            }
            if(pilas != null) {
                r.setPilas("SI");
            } else {
                r.setPilas("NO");
            }
            if(otroBool != null) {
                r.setOtro(otro);
            } else {
                r.setOtro("");
            }
            r.setEstado("INGRESADO");
            r.setTrabajo_realizado("");
            r.setHoras_mano_obra(0);
            r.setCosto(0.0);
            r.setGarantia("");
            r.setFecha_fin(LocalDate.parse("1998-06-22"));
            r.setFecha_retiro(LocalDate.parse("1998-06-22"));
            session.persist(r);

            session.getTransaction().commit();

            session.close();
        }

        response.sendRedirect(request.getContextPath() + "/verArticulo?clienteId=" + clienteId +
                "&articuloId=" + articuloId);
    }
}
