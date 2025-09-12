package rdt.arrieta.casa.categorias;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Properties;
import rdt.arrieta.casa.clases.tamanio_pantalla;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearTamanioPantalla")
public class CrearTamanioPantallaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String tamanio = request.getParameter("tamanio");
        tamanio_pantalla t = new tamanio_pantalla();
        t.setTamanio(tamanio);

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
