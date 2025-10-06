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
import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verInventarioLed")
public class VerInventarioLedServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String tamanio = request.getParameter("tamanio");

        String cantidad = request.getParameter("cantidad");

        List<Inventario_led> leds = new ArrayList<>();

        Session session = DBManager.getSessionFactory().openSession();

        if (request.getParameter("cantidad") == null || request.getParameter("cantidad").isEmpty()) {
            String hql = "SELECT il FROM Inventario_led il " +
                    "WHERE (:tamanio IS NULL OR lower(il.led.tamanio) LIKE :tamanio) " +
                    "AND (il.inventario.cantidad <> 0)";

            Query<Inventario_led> query = session.createQuery(hql, Inventario_led.class);

            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);

            leds = query.getResultList();

            hql = "SELECT il FROM Inventario_led il " +
                    "WHERE (:tamanio IS NULL OR lower(il.led.tamanio) LIKE :tamanio) " +
                    "AND (il.inventario.cantidad = 0)";

            query = session.createQuery(hql, Inventario_led.class);

            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);

            leds.addAll(query.getResultList());
        } else {
            int cant = Integer.parseInt(cantidad);

            String hql = "SELECT il FROM Inventario_led il " +
                    "WHERE (:tamanio IS NULL OR lower(il.led.tamanio) LIKE :tamanio) " +
                    "AND (:cantidad IS NULL OR il.inventario.cantidad = :cantidad)";

            Query<Inventario_led> query = session.createQuery(hql, Inventario_led.class);

            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);
            query.setParameter("cantidad", cant);

            leds = query.getResultList();
        }

        session.close();

        request.setAttribute("leds", leds);
        request.getRequestDispatcher("verInventarioLed.jsp").forward(request, response);
    }
}
