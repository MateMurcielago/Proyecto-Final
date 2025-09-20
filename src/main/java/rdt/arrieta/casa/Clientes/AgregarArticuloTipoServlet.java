package rdt.arrieta.casa.Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarArticuloTipo")
public class AgregarArticuloTipoServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long idCliente = Long.parseLong(request.getParameter("idCliente"));

        Session session = DBManager.getSessionFactory().openSession();
        Query<tipo_articulo> query = session.createQuery("from tipo_articulo", tipo_articulo.class);
        List<tipo_articulo> tipos = query.list();
        session.close();

        request.setAttribute("idCliente", idCliente);
        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("agregarArticuloTipo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long idCliente = Long.parseLong(request.getParameter("idCliente"));
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        response.sendRedirect(request.getContextPath() + "/agregarArticuloMarca?idCliente=" + idCliente
                + "&tipoId=" + tipoId);
    }
}
