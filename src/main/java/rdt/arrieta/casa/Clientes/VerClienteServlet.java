package rdt.arrieta.casa.Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verCliente")
public class VerClienteServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long ClienteId = Long.parseLong(request.getParameter("id"));

        Session session = DBManager.getSessionFactory().openSession();

        Cliente cliente = session.get(Cliente.class, ClienteId);
        cliente.getArticulos().size(); //para que Hibernate cargue la lista
        List<ArticuloCliente> articulos = cliente.getArticulos();

        request.setAttribute("id", ClienteId);
        request.setAttribute("cliente", cliente);
        request.setAttribute("articulos", articulos);

        session.close();

        request.getRequestDispatcher("verCliente.jsp").forward(request, response);
    }
}
