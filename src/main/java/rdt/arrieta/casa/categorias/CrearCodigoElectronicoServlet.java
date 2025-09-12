package rdt.arrieta.casa.categorias;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.nombre_electronico;
import rdt.arrieta.casa.clases.codigo_electronico;
import rdt.arrieta.casa.clases.Inventario;
import rdt.arrieta.casa.clases.Inventario_electronico;
import rdt.arrieta.casa.clases.embebidas.id_inventario_electronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearCodigoElectronico")
public class CrearCodigoElectronicoServlet extends HttpServlet {
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
        Query<nombre_electronico> query = session.createQuery(
                        "from nombre_electronico n where n.tipo.id = :catId", nombre_electronico.class)
                .setParameter("catId", tipoId);
        List<nombre_electronico> nombres = query.list();
        session.close();

        request.setAttribute("tipoId", tipoId);
        request.setAttribute("nombres", nombres);

        request.getRequestDispatcher("crearCodigoElectronico.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("codigo");
        Long nombreId = Long.parseLong(request.getParameter("nombreId"));

        Double precio = Double.parseDouble(request.getParameter("precio"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener el nombre de electrónico seleccionado
            nombre_electronico nombre = session.get(nombre_electronico.class, nombreId);

            codigo_electronico c = new codigo_electronico();
            c.setNombre(nombre);
            c.setCodigo(codigo);
            session.persist(c);

            Inventario i = new Inventario();
            i.setCantidad(0L);
            i.setPrecio(precio);
            session.persist(i);

            id_inventario_electronico ieId = new id_inventario_electronico(i.getId(), c.getId());

            Inventario_electronico ie = new Inventario_electronico();
            ie.setId(ieId);
            ie.setInventario(i);
            ie.setElectronico(c);
            session.persist(ie);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("index.jsp");
    }
}
