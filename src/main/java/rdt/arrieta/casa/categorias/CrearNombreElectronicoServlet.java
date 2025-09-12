package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_electronico;
import rdt.arrieta.casa.clases.nombre_electronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearNombreElectronico")
public class CrearNombreElectronicoServlet extends HttpServlet {
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
        Query<tipo_electronico> query =
                session.createQuery("from tipo_electronico", tipo_electronico.class);
        List<tipo_electronico> tipos = query.list();
        session.close();

        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("crearNombreElectronico.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Obtener el tipo de electronico seleccionado
            tipo_electronico tipo = session.get(tipo_electronico.class, tipoId);

            nombre_electronico n = new nombre_electronico();
            n.setNombre(nombre);
            n.setTipo(tipo);
            session.persist(n);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
