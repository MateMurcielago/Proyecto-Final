package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearFallaComunMarca")
public class CrearFallaComunMarcaServlet extends HttpServlet {
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

        Session session = DBManager.getSessionFactory().openSession();
        String tipo = session.get(tipo_articulo.class, tipoId).getTipo();
        Query<marca_articulo> query = session.createQuery(
                        "from marca_articulo m where m.tipo.id = :catId", marca_articulo.class)
                .setParameter("catId", tipoId);
        List<marca_articulo> marcas = query.list();
        session.close();

        request.setAttribute("tipoId", tipoId);
        request.setAttribute("tipo", tipo);
        request.setAttribute("marcas", marcas);

        request.getRequestDispatcher("crearFallaComunMarca.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));
        Long marcaId = Long.parseLong(request.getParameter("marcaId"));

        response.sendRedirect(request.getContextPath() + "/crearFallaComun?tipoId=" + tipoId + "&marcaId=" +
                marcaId);
    }
}
