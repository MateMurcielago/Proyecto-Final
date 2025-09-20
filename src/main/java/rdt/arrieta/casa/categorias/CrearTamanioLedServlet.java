package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Properties;
import rdt.arrieta.casa.clases.tamanio_led;
import rdt.arrieta.casa.clases.Inventario;
import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.clases.embebidas.id_inventario_led;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;

@WebServlet("/crearTamanioLed")
public class CrearTamanioLedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String tamanio = request.getParameter("tamanio");

        Double precio = Double.parseDouble(request.getParameter("precio"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            tamanio_led t = new tamanio_led();
            t.setTamanio(tamanio);
            session.persist(t);

            Inventario i = new Inventario();
            i.setPrecio(precio);
            i.setCantidad(0L);
            session.persist(i);

            id_inventario_led ilId = new id_inventario_led(i.getId(), t.getId());

            Inventario_led il = new Inventario_led();
            il.setId(ilId);
            il.setInventario(i);
            il.setLed(t);
            session.persist(il);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
