package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.Inventario_pantalla;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ReparacionPantalla;
import rdt.arrieta.casa.clases.tamanio_pantalla;
import rdt.arrieta.casa.clases.codigo_pantalla;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarPantallaCodigo")
public class AgregarPantallaCodigoServlet extends HttpServlet {
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

        Long tamanioId = Long.parseLong(request.getParameter("tamanioId"));

        Session session = DBManager.getSessionFactory().openSession();
        String tamanio = session.get(tamanio_pantalla.class, tamanioId).getTamanio();
        Query<codigo_pantalla> query = session.createQuery(
                "FROM codigo_pantalla c WHERE c.tamanio.id = :id", codigo_pantalla.class)
                .setParameter("id", tamanioId);
        List<codigo_pantalla> codigos = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tamanio", tamanio);
        request.setAttribute("codigos", codigos);

        request.getRequestDispatcher("agregarPantallaCodigo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long codigoId = Long.parseLong(request.getParameter("codigoId"));
        String inventario = request.getParameter("inventario");

        int aux = 0;
        String mensaje = "";

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener la reparación
            Reparacion reparacion = session.get(Reparacion.class, reparacionId);

            //Obtener el repuesto
            codigo_pantalla pantalla = session.get(codigo_pantalla.class, codigoId);

            ReparacionPantalla rp = new ReparacionPantalla(reparacion, pantalla);

            //Restar del inventario
            if(inventario != null) {
                Query<Inventario_pantalla> query = session.createQuery(
                        "FROM Inventario_pantalla ip WHERE ip.pantalla.id = :id",
                        Inventario_pantalla.class).setParameter("id", codigoId);
                Inventario_pantalla ip = query.list().getFirst();

                if(ip.getInventario().getCantidad() > 0) {
                    ip.getInventario().setCantidad(ip.getInventario().getCantidad() - 1);
                    session.persist(rp);
                    if(ip.getInventario().getCantidad() == 0L) aux = 1;
                } else {
                    mensaje = "alert('No hay unidades del repuesto requerido en inventario');";
                    aux = 2;
                }
            } else {
                session.persist(rp);
            }

            session.getTransaction().commit();
            session.close();
        }

        if(aux == 1) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Se ha utilizado el último repuesto disponible');");
            out.println("window.location.href = 'verReparacion?clienteId=" + clienteId + "&articuloId=" +
                    articuloId + "&reparacionId=" + reparacionId + "';");
            out.println("</script>");
            out.close();
        } else if(aux == 2) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<script type=\"text/javascript\">");
            out.println(mensaje);
            out.println("window.location.href = 'verReparacion?clienteId=" + clienteId + "&articuloId=" +
                    articuloId + "&reparacionId=" + reparacionId + "';");
            out.println("</script>");
            out.close();
        } else {
            response.sendRedirect(request.getContextPath() + "/verReparacion?clienteId=" + clienteId +
                    "&articuloId=" + articuloId + "&reparacionId=" + reparacionId);
        }
    }
}
