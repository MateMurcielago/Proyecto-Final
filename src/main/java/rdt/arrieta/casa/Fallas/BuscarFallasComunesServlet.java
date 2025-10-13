package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.FallaComun;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/buscarFallasComunes")
public class BuscarFallasComunesServlet extends HttpServlet {
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
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");

        Session session = DBManager.getSessionFactory().openSession();

        String hql = "SELECT f FROM FallaComun f " +
                "WHERE (:tipo IS NULL OR lower(f.articulo.marca.tipo.tipo) LIKE :tipo) " +
                "AND (:marca IS NULL OR lower(f.articulo.marca.marca) LIKE :marca) " +
                "AND (:modelo IS NULL OR lower(f.articulo.modelo) LIKE :modelo)";

        Query<FallaComun> query = session.createQuery(hql, FallaComun.class);

        query.setParameter("tipo",
                (tipo != null && !tipo.isEmpty()) ? "%" + tipo.toLowerCase() + "%" : null);
        query.setParameter("marca",
                (marca != null && !marca.isEmpty()) ? "%" + marca.toLowerCase() + "%" : null);
        query.setParameter("modelo",
                (modelo != null && !modelo.isEmpty()) ? "%" + modelo.toLowerCase() + "%" : null);

        List<FallaComun> fallas = query.getResultList();

        session.close();

        request.setAttribute("fallas", fallas);
        request.getRequestDispatcher("buscarFallasComunes.jsp").forward(request, response);
    }
}
