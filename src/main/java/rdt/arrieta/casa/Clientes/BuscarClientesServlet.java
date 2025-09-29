package rdt.arrieta.casa.Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/buscarClientes")
public class BuscarClientesServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        Session session = DBManager.getSessionFactory().openSession();

        StringBuilder hql = new StringBuilder("from Cliente c where 1=1");

        if(nombre != null && !nombre.trim().isEmpty()) {
            hql.append(" and lower(c.nombre) like :nombre");
        }
        if(apellido != null && !apellido.trim().isEmpty()) {
            hql.append(" and lower(c.apellido) like :apellido");
        }

        Query<Cliente> query = session.createQuery(hql.toString(), Cliente.class);

        if(nombre != null && !nombre.trim().isEmpty()) {
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
        }
        if(apellido != null && !apellido.trim().isEmpty()) {
            query.setParameter("apellido", "%" + apellido.toLowerCase() + "%");
        }

        List<Cliente> clientes = query.getResultList();

        session.close();

        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("buscarClientes.jsp").forward(request, response);
    }
}
