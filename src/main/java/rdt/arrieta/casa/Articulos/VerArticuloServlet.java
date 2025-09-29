package rdt.arrieta.casa.Articulos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.Reparacion;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.embebidas.ArticuloClienteId;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verArticulo")
public class VerArticuloServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        ArticuloClienteId id = new ArticuloClienteId(clienteId, articuloId);


        Session session = DBManager.getSessionFactory().openSession();

        ArticuloCliente articulo = session.get(ArticuloCliente.class, id);
        articulo.getReparaciones().size(); //para que Hibernate cargue la lista
        List<Reparacion> reparacioness = articulo.getReparaciones();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);

        request.setAttribute("articulo", articulo);
        request.setAttribute("reparaciones", reparacioness);

        session.close();

        request.getRequestDispatcher("verArticulo.jsp").forward(request, response);
    }
}
