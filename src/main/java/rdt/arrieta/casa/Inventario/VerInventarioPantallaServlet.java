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
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verInventarioPantalla")
public class VerInventarioPantallaServlet extends HttpServlet {
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
        String tamanio = request.getParameter("tamanio");
        String codigo = request.getParameter("codigo");

        String cantidad = request.getParameter("cantidad");

        List<Inventario_pantalla> pantallas = new ArrayList<>();

        Session session = DBManager.getSessionFactory().openSession();

        if (request.getParameter("cantidad") == null || request.getParameter("cantidad").isEmpty()) {
            String hql = "SELECT ip FROM Inventario_pantalla ip " +
                    "WHERE (:tipo IS NULL OR lower(ip.pantalla.tipo.tipo) LIKE :tipo) " +
                    "AND (:tamanio IS NULL OR lower(ip.pantalla.tamanio.tamanio) LIKE :tamanio) " +
                    "AND (:codigo IS NULL OR lower(ip.pantalla.codigo) LIKE :codigo) " +
                    "AND (ip.inventario.cantidad <> 0)";

            Query<Inventario_pantalla> query = session.createQuery(hql, Inventario_pantalla.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);

            pantallas = query.getResultList();

            hql = "SELECT ip FROM Inventario_pantalla ip " +
                    "WHERE (:tipo IS NULL OR lower(ip.pantalla.tipo.tipo) LIKE :tipo) " +
                    "AND (:tamanio IS NULL OR lower(ip.pantalla.tamanio.tamanio) LIKE :tamanio) " +
                    "AND (:codigo IS NULL OR lower(ip.pantalla.codigo) LIKE :codigo) " +
                    "AND (ip.inventario.cantidad = 0)";

            query = session.createQuery(hql, Inventario_pantalla.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);

            pantallas.addAll(query.getResultList());
        } else {
            int cant = Integer.parseInt(cantidad);

            String hql = "SELECT ip FROM Inventario_pantalla ip " +
                    "WHERE (:tipo IS NULL OR lower(ip.pantalla.tipo.tipo) LIKE :tipo) " +
                    "AND (:tamanio IS NULL OR lower(ip.pantalla.tamanio.tamanio) LIKE :tamanio) " +
                    "AND (:codigo IS NULL OR lower(ip.pantalla.codigo) LIKE :codigo) " +
                    "AND (:cantidad IS NULL OR ip.inventario.cantidad = :cantidad)";

            Query<Inventario_pantalla> query = session.createQuery(hql, Inventario_pantalla.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("tamanio",
                    (tamanio != null && !tamanio.isEmpty()) ? "%" + tamanio.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);
            query.setParameter("cantidad", cant);

            pantallas = query.getResultList();
        }

        session.close();

        request.setAttribute("pantallas", pantallas);
        request.getRequestDispatcher("verInventarioPantalla.jsp").forward(request, response);
    }
}
