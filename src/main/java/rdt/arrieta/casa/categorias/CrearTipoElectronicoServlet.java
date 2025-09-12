package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_electronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearTipoElectronico")
public class CrearTipoElectronicoServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String tipo = request.getParameter("tipo");
        tipo_electronico t = new tipo_electronico();
        t.setTipo(tipo);

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
