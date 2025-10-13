package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.*;
import rdt.arrieta.casa.clases.embebidas.ReparacionElectronicoId;
import rdt.arrieta.casa.clases.intermedias.ReparacionElectronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarElectronicoCodigo")
public class AgregarElectronicoCodigoServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        Long nombreId = Long.parseLong(request.getParameter("nombreId"));

        Session session = DBManager.getSessionFactory().openSession();
        String tipo = session.get(tipo_electronico.class, tipoId).getTipo();
        String nombre = session.get(nombre_electronico.class, nombreId).getNombre();
        Query<codigo_electronico> query = session.createQuery(
                "FROM codigo_electronico c WHERE c.nombre.id = :id", codigo_electronico.class)
                .setParameter("id", nombreId);
        List<codigo_electronico> codigos = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tipo", tipo);
        request.setAttribute("nombre", nombre);
        request.setAttribute("codigos", codigos);

        request.getRequestDispatcher("agregarElectronicoCodigo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long codigoId = Long.parseLong(request.getParameter("codigoId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String inventario = request.getParameter("inventario");

        int aux = 0;
        String mensaje = "";

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener la reparación
            Reparacion reparacion = session.get(Reparacion.class, reparacionId);

            //Obtener el repuesto
            codigo_electronico electronico = session.get(codigo_electronico.class, codigoId);

            ReparacionElectronico re = new ReparacionElectronico(reparacion, electronico);
            re.setCantidad(cantidad);

            //Restar del inventario
            if(inventario != null) {
                Query<Inventario_electronico> query = session.createQuery(
                                "FROM Inventario_electronico ie WHERE ie.electronico.id = :id",
                                Inventario_electronico.class)
                        .setParameter("id", codigoId);
                Inventario_electronico ie = query.list().getFirst();

                if(ie.getInventario().getCantidad() - cantidad >= 0) { //Si la cantidad indicada es mayor que el
                    ie.getInventario().setCantidad(ie.getInventario().getCantidad() - cantidad); //inventario, no
                    session.persist(re);                                                         //guarda nada
                    if (ie.getInventario().getCantidad() == 0L) aux = 1;
                } else {
                    if(ie.getInventario().getCantidad() > 0) {
                        mensaje = "alert('La cantidad indicada es mayor que la disponible');";
                    } else {
                        mensaje = "alert('No hay unidades del repuesto requerido en inventario');";
                    }
                    aux = 2;
                }
            } else {
                session.persist(re);
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
