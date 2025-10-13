package rdt.arrieta.casa.Inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.Inventario_electronico;
import rdt.arrieta.casa.clases.Inventario_pantalla;
import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.clases.embebidas.id_inventario_electronico;
import rdt.arrieta.casa.clases.embebidas.id_inventario_pantalla;
import rdt.arrieta.casa.clases.embebidas.id_inventario_led;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/editarCantidadInventario")
public class EditarCantidadInventarioServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String tipo = request.getParameter("tipo");
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        Long inventarioId = Long.parseLong(request.getParameter("inventarioId"));
        String accion = request.getParameter("accion");

        request.setAttribute("tipo", tipo);
        request.setAttribute("repuestoId", repuestoId);
        request.setAttribute("inventarioId", inventarioId);
        request.setAttribute("accion", accion);

        request.getRequestDispatcher("editarCantidadInventario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        Long inventarioId = Long.parseLong(request.getParameter("inventarioId"));
        String accion = request.getParameter("accion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        String paginaAnterior = "";

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        switch(tipo) {
            case "electronico":
                id_inventario_electronico ide = new id_inventario_electronico(inventarioId, repuestoId);
                Inventario_electronico e = session.get(Inventario_electronico.class, ide);

                if(accion.equals("sumar")) {
                    e.getInventario().setCantidad(e.getInventario().getCantidad() + cantidad);
                } else {
                    if(e.getInventario().getCantidad() < cantidad) {
                        e.getInventario().setCantidad(0L);
                    } else {
                        e.getInventario().setCantidad(e.getInventario().getCantidad() - cantidad);
                    }
                }

                paginaAnterior = "verInventarioElectronico";
                break;
            case "pantalla":
                id_inventario_pantalla idp = new id_inventario_pantalla(inventarioId, repuestoId);
                Inventario_pantalla p = session.get(Inventario_pantalla.class, idp);

                if(accion.equals("sumar")) {
                    p.getInventario().setCantidad(p.getInventario().getCantidad() + cantidad);
                } else {
                    if(p.getInventario().getCantidad() < cantidad) {
                        p.getInventario().setCantidad(0L);
                    } else {
                        p.getInventario().setCantidad(p.getInventario().getCantidad() - cantidad);
                    }
                }

                paginaAnterior = "verInventarioPantalla";
                break;
            case "led":
                id_inventario_led idl = new id_inventario_led(inventarioId, repuestoId);
                Inventario_led l = session.get(Inventario_led.class, idl);

                if(accion.equals("sumar")) {
                    l.getInventario().setCantidad(l.getInventario().getCantidad() + cantidad);
                } else {
                    if(l.getInventario().getCantidad() < cantidad) {
                        l.getInventario().setCantidad(0L);
                    } else {
                        l.getInventario().setCantidad(l.getInventario().getCantidad() - cantidad);
                    }
                }

                paginaAnterior = "verInventarioLed";
                break;
        }

        session.getTransaction().commit();
        session.close();

        response.sendRedirect(paginaAnterior);
    }
}
