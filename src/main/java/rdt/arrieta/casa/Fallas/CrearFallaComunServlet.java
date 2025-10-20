package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.FallaComun;
import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearFallaComun")
public class CrearFallaComunServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long tipoId = Long.parseLong(request.getParameter("tipoId"));
        Long marcaId = Long.parseLong(request.getParameter("marcaId"));

        Session session = DBManager.getSessionFactory().openSession();
        String tipo = session.get(tipo_articulo.class, tipoId).getTipo();
        String marca = session.get(marca_articulo.class, marcaId).getMarca();
        Query<modelo_articulo> query = session.createQuery(
                        "from modelo_articulo m where m.marca.id = :catId", modelo_articulo.class)
                .setParameter("catId", marcaId);
        List<modelo_articulo> modelos = query.list();
        session.close();

        request.setAttribute("tipo", tipo);
        request.setAttribute("marca", marca);
        request.setAttribute("modelos", modelos);

        request.getRequestDispatcher("crearFallaComun.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long modeloId = Long.parseLong(request.getParameter("modeloId"));
        String resumen = request.getParameter("resumen");
        String falla = request.getParameter("falla");
        String solucion = request.getParameter("solucion");

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        //Obtener el artículo
        modelo_articulo articulo = session.get(modelo_articulo.class, modeloId);

        FallaComun f = new FallaComun();
        f.setArticulo(articulo);
        f.setResumen(resumen);
        f.setFalla(falla);
        f.setSolucion(solucion);
        session.persist(f);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect(request.getContextPath() + "/verFallaComun?fallaId=" + f.getId());
    }
}
