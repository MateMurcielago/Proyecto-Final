package rdt.arrieta.casa.Articulos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/buscarArticulos")
public class BuscarArticulosServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String tipo = request.getParameter("tipo");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        Session session = DBManager.getSessionFactory().openSession();

        String hql = "SELECT ac FROM ArticuloCliente ac JOIN ac.cliente c JOIN ac.articulo a " +
                "WHERE (:tipo IS NULL OR lower(a.marca.tipo.tipo) LIKE :tipo) " +
                "AND (:marca IS NULL OR lower(a.marca.marca) LIKE :marca) " +
                "AND (:modelo IS NULL OR lower(a.modelo) LIKE :modelo) " +
                "AND (:nombre IS NULL OR lower(c.nombre) LIKE :nombre) " +
                "AND (:apellido IS NULL OR lower(c.apellido) LIKE :apellido)";

        Query<ArticuloCliente> query = session.createQuery(hql, ArticuloCliente.class);

        query.setParameter("tipo",
                (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
        query.setParameter("marca",
                (marca != null && !marca.isEmpty()) ? "%" + marca.toLowerCase() + "%" : null);
        query.setParameter("modelo",
                (modelo != null && !modelo.isEmpty()) ? "%" + modelo.toLowerCase() + "%" : null);

        query.setParameter("nombre",
                (nombre != null && !nombre.isEmpty()) ? "%" + nombre.toLowerCase() + "%" : null);
        query.setParameter("apellido",
                (apellido != null && !apellido.isEmpty()) ? "%" + apellido.toLowerCase() + "%" : null);

        List<ArticuloCliente> articulos = query.getResultList();

        session.close();

        request.setAttribute("articulos", articulos);
        request.getRequestDispatcher("buscarArticulos.jsp").forward(request, response);
    }
}
