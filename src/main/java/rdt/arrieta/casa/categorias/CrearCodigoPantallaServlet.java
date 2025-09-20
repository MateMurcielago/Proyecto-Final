package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tamanio_pantalla;
import rdt.arrieta.casa.clases.tipo_pantalla;
import rdt.arrieta.casa.clases.codigo_pantalla;
import rdt.arrieta.casa.clases.Inventario;
import rdt.arrieta.casa.clases.Inventario_pantalla;
import rdt.arrieta.casa.clases.embebidas.id_inventario_pantalla;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearCodigoPantalla")
public class CrearCodigoPantallaServlet extends HttpServlet {
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
        Query<tamanio_pantalla> query1 =
                session.createQuery("from tamanio_pantalla", tamanio_pantalla.class);
        Query<tipo_pantalla> query2 = session.createQuery("from tipo_pantalla", tipo_pantalla.class);
        List<tamanio_pantalla> tamanios = query1.list();
        List<tipo_pantalla> tipos = query2.list();
        session.close();

        request.setAttribute("tamanios", tamanios);
        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("crearCodigoPantalla.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("codigo");
        Long tamanioId = Long.parseLong(request.getParameter("tamanioId"));
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        Double precio = Double.parseDouble(request.getParameter("precio"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            //Obtener el tamaño de pantalla seleccionado
            tamanio_pantalla tamanio = session.get(tamanio_pantalla.class, tamanioId);

            // Obtener el tipo de pantalla seleccionado
            tipo_pantalla tipo = session.get(tipo_pantalla.class, tipoId);

            codigo_pantalla c = new codigo_pantalla();
            c.setTamanio(tamanio);
            c.setTipo(tipo);
            c.setCodigo(codigo);
            session.persist(c);

            Inventario i = new Inventario();
            i.setCantidad(0L);
            i.setPrecio(precio);
            session.persist(i);

            id_inventario_pantalla ipId = new id_inventario_pantalla(i.getId(), c.getId());

            Inventario_pantalla ip = new Inventario_pantalla();
            ip.setId(ipId);
            ip.setInventario(i);
            ip.setPantalla(c);
            session.persist(ip);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
