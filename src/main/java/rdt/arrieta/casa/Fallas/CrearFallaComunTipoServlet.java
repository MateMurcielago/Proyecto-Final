package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearFallaComunTipo")
public class CrearFallaComunTipoServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Session session = DBManager.getSessionFactory().openSession();
        Query<tipo_articulo> query = session.createQuery("from tipo_articulo", tipo_articulo.class);
        List<tipo_articulo> tipos = query.list();
        session.close();

        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("crearFallaComunTipo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        response.sendRedirect(request.getContextPath() + "/crearFallaComunMarca?tipoId=" + tipoId);
    }
}
