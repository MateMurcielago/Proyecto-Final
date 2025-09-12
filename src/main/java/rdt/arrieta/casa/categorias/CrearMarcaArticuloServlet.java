package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearMarcaArticulo")
public class CrearMarcaArticuloServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Session session = DBManager.getSessionFactory().openSession();
        Query<tipo_articulo> query = session.createQuery("from tipo_articulo", tipo_articulo.class);
        List<tipo_articulo> tipos = query.list();
        session.close();

        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("crearMarcaArticulo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String marca = request.getParameter("marca");
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Obtener el tipo de artículo seleccionado
            tipo_articulo tipo = session.get(tipo_articulo.class, tipoId);

            marca_articulo m = new marca_articulo();
            m.setMarca(marca);
            m.setTipo(tipo);
            session.persist(m);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
