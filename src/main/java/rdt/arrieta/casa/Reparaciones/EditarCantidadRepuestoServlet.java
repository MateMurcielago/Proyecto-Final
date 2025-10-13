package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.*;
import rdt.arrieta.casa.clases.embebidas.ReparacionLedId;
import rdt.arrieta.casa.clases.intermedias.ReparacionElectronico;
import rdt.arrieta.casa.clases.intermedias.ReparacionLed;
import rdt.arrieta.casa.clases.embebidas.ReparacionElectronicoId;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/editarCantidadRepuesto")
public class EditarCantidadRepuestoServlet extends HttpServlet {
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
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        String tipo = request.getParameter("tipo");

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("repuestoId", repuestoId);
        request.setAttribute("tipo", tipo);

        Session session = DBManager.getSessionFactory().openSession();

        if(tipo.equals("electronico")) {
            codigo_electronico repuesto = session.get(codigo_electronico.class, repuestoId);
            request.setAttribute("codigo", repuesto.getCodigo());
            request.setAttribute("nombre", repuesto.getNombre().getNombre());
            request.setAttribute("tipoe", repuesto.getNombre().getTipo().getTipo());
        } else {
            tamanio_led repuesto = session.get(tamanio_led.class, repuestoId);
            request.setAttribute("tamanio", repuesto.getTamanio());
        }

        session.close();

        request.getRequestDispatcher("editarCantidadRepuesto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        String tipo = request.getParameter("tipo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String inventario = request.getParameter("inventario");

        int aux = 0;
        String mensaje = "";

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener la reparación
            Reparacion reparacion = session.get(Reparacion.class, reparacionId);

            if(tipo.equals("electronico")) {
                //Obtener el repuesto
                ReparacionElectronicoId ide = new ReparacionElectronicoId(reparacionId, repuestoId);
                ReparacionElectronico re = session.get(ReparacionElectronico.class, ide);

                re.setCantidad(cantidad);

                //Restar del inventario
                if(inventario != null) {
                    Query<Inventario_electronico> query = session.createQuery(
                            "FROM Inventario_electronico ie WHERE ie.electronico.id = :id",
                            Inventario_electronico.class).setParameter("id", repuestoId);
                    Inventario_electronico ie = query.list().getFirst();

                    if(ie.getInventario().getCantidad() - cantidad >= 0) {
                        ie.getInventario().setCantidad(ie.getInventario().getCantidad() - cantidad);
                        session.getTransaction().commit();
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
                    session.getTransaction().commit();
                }
            } else {
                //Obtener el repuesto
                ReparacionLedId idl = new ReparacionLedId(reparacionId, repuestoId);
                ReparacionLed rl = session.get(ReparacionLed.class, idl);

                rl.setCantidad(cantidad);

                //Restar del inventario
                if(inventario != null) {
                    Query<Inventario_led> query = session.createQuery(
                            "FROM Inventario_led il WHERE ie.led.id = :id", Inventario_led.class)
                            .setParameter("id", repuestoId);
                    Inventario_led il = query.list().getFirst();

                    if(il.getInventario().getCantidad() - cantidad >= 0) {
                        il.getInventario().setCantidad(il.getInventario().getCantidad() - cantidad);
                        session.getTransaction().commit();
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
                    session.getTransaction().commit();
                }
            }

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
