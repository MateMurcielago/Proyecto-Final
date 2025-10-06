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
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verInventarioElectronico")
public class VerInventarioElectronicoServlet extends HttpServlet {
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
        String nombre = request.getParameter("nombre");
        String codigo = request.getParameter("codigo");

        String cantidad = request.getParameter("cantidad");

        List<Inventario_electronico> electronicos = new ArrayList<>();

        Session session = DBManager.getSessionFactory().openSession();

        if (request.getParameter("cantidad") == null || request.getParameter("cantidad").isEmpty()) {
            String hql = "SELECT ie FROM Inventario_electronico ie " +
                    "WHERE (:tipo IS NULL OR lower(ie.electronico.nombre.tipo.tipo) LIKE :tipo) " +
                    "AND (:nombre IS NULL OR lower(ie.electronico.nombre.nombre) LIKE :nombre) " +
                    "AND (:codigo IS NULL OR lower(ie.electronico.codigo) LIKE :codigo) " +
                    "AND (ie.inventario.cantidad <> 0)";

            Query<Inventario_electronico> query = session.createQuery(hql, Inventario_electronico.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("nombre",
                    (nombre != null && !nombre.isEmpty()) ? "%" + nombre.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);

            electronicos = query.getResultList();

            hql = "SELECT ie FROM Inventario_electronico ie " +
                    "WHERE (:tipo IS NULL OR lower(ie.electronico.nombre.tipo.tipo) LIKE :tipo) " +
                    "AND (:nombre IS NULL OR lower(ie.electronico.nombre.nombre) LIKE :nombre) " +
                    "AND (:codigo IS NULL OR lower(ie.electronico.codigo) LIKE :codigo) " +
                    "AND (ie.inventario.cantidad = 0)";

            query = session.createQuery(hql, Inventario_electronico.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("nombre",
                    (nombre != null && !nombre.isEmpty()) ? "%" + nombre.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);

            electronicos.addAll(query.getResultList());
        } else {
            int cant = Integer.parseInt(cantidad);

            String hql = "SELECT ie FROM Inventario_electronico ie " +
                    "WHERE (:tipo IS NULL OR lower(ie.electronico.nombre.tipo.tipo) LIKE :tipo) " +
                    "AND (:nombre IS NULL OR lower(ie.electronico.nombre.nombre) LIKE :nombre) " +
                    "AND (:codigo IS NULL OR lower(ie.electronico.codigo) LIKE :codigo) " +
                    "AND (:cantidad IS NULL OR ie.inventario.cantidad = :cantidad)";

            Query<Inventario_electronico> query = session.createQuery(hql, Inventario_electronico.class);

            query.setParameter("tipo",
                    (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
            query.setParameter("nombre",
                    (nombre != null && !nombre.isEmpty()) ? "%" + nombre.toLowerCase() + "%" : null);
            query.setParameter("codigo",
                    (codigo != null && !codigo.isEmpty()) ? "%" + codigo.toLowerCase() + "%" : null);
            query.setParameter("cantidad", cant);

            electronicos = query.getResultList();
        }
        session.close();

        request.setAttribute("electronicos", electronicos);
        request.getRequestDispatcher("verInventarioElectronico.jsp").forward(request, response);
    }
}
