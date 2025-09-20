package rdt.arrieta.casa.Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarArticuloModelo")
public class AgregarArticuloModeloServlet extends HttpServlet {
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
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));
        Long marcaId = Long.parseLong(request.getParameter("marcaId"));

        Session session = DBManager.getSessionFactory().openSession();
        tipo_articulo t = session.get(tipo_articulo.class, tipoId);
        String tipo = t.getTipo();
        marca_articulo m = session.get(marca_articulo.class, marcaId);
        String marca = m.getMarca();
        Query<modelo_articulo> query = session.createQuery(
                        "from modelo_articulo m where m.marca.id = :catId", modelo_articulo.class)
                .setParameter("catId", marcaId);
        List<modelo_articulo> modelos = query.list();
        session.close();

        request.setAttribute("idCliente", idCliente);
        request.setAttribute("tipo", tipo);
        request.setAttribute("marca", marca);
        request.setAttribute("modelos", modelos);

        request.getRequestDispatcher("agregarArticuloModelo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long idCliente = Long.parseLong(request.getParameter("idCliente"));
        Long modeloId = Long.parseLong(request.getParameter("modeloId"));

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();

            //Obtener el cliente
            Cliente cliente = session.get(Cliente.class, idCliente);

            //Obtener el artículo
            modelo_articulo articulo = session.get(modelo_articulo.class, modeloId);

            ArticuloCliente ac = new ArticuloCliente(cliente, articulo);
            session.persist(ac);

            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect(request.getContextPath() + "/verCliente?id=" + idCliente);
    }
}
