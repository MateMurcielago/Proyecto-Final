package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearModeloArticulo")
public class CrearModeloArticuloServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        Session session = DBManager.getSessionFactory().openSession();
        Query<marca_articulo> query = session.createQuery(
                "from marca_articulo m where m.tipo.id = :catId", marca_articulo.class)
                .setParameter("catId", tipoId);
        List<marca_articulo> marcas = query.list();
        session.close();

        request.setAttribute("tipoId", tipoId);
        request.setAttribute("marcas", marcas);

        request.getRequestDispatcher("crearModeloArticulo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelo = request.getParameter("modelo");
        Long marcaId = Long.parseLong(request.getParameter("marcaId"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Obtener la marca de artículo seleccionado
            marca_articulo marca = session.get(marca_articulo.class, marcaId);

            modelo_articulo m = new modelo_articulo();
            m.setModelo(modelo);
            m.setMarca(marca);
            session.persist(m);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
