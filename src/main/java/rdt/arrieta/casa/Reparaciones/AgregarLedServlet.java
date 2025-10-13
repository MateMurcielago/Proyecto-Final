package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ReparacionLed;
import rdt.arrieta.casa.clases.tamanio_led;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarLed")
public class AgregarLedServlet extends HttpServlet {
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
        Query<tamanio_led> query = session.createQuery("FROM tamanio_led", tamanio_led.class);
        List<tamanio_led> tamanios = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tamanios", tamanios);

        request.getRequestDispatcher("agregarLed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long tamanioId = Long.parseLong(request.getParameter("tamanioId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String inventario = request.getParameter("inventario");

        int aux = 0;
        String mensaje = "";

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener la reparación
            Reparacion reparacion = session.get(Reparacion.class, reparacionId);

            //Obtener el repuesto
            tamanio_led led = session.get(tamanio_led.class, tamanioId);

            ReparacionLed rl = new ReparacionLed(reparacion, led);
            rl.setCantidad(cantidad);

            //Restar del inventario
            if(inventario != null) {
                Query<Inventario_led> query = session.createQuery(
                        "FROM Inventario_led il WHERE il.led.id = :id", Inventario_led.class)
                        .setParameter("id", tamanioId);
                Inventario_led il = query.list().getFirst();

                if(il.getInventario().getCantidad() - cantidad >= 0) {
                    il.getInventario().setCantidad(il.getInventario().getCantidad() - cantidad);
                    session.persist(rl);
                    if (il.getInventario().getCantidad() == 0L) aux = 1;
                } else {
                    if(il.getInventario().getCantidad() > 0) {
                        mensaje = "alert('La cantidad indicada es mayor que la disponible');";
                    } else {
                        mensaje = "alert('No hay unidades del repuesto requerido en inventario');";
                    }
                    aux = 2;
                }
            } else {
                session.persist(rl);
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
