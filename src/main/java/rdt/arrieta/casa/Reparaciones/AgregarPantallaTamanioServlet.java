package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tamanio_pantalla;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarPantallaTamanio")
public class AgregarPantallaTamanioServlet extends HttpServlet {
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
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Session session = DBManager.getSessionFactory().openSession();
        Query<tamanio_pantalla> query =
                session.createQuery("FROM tamanio_pantalla", tamanio_pantalla.class);
        List<tamanio_pantalla> tamanios = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tamanios", tamanios);

        request.getRequestDispatcher("agregarPantallaTamanio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long tamanioId = Long.parseLong(request.getParameter("tamanioId"));

        response.sendRedirect(request.getContextPath() + "/agregarPantallaCodigo?clienteId=" + clienteId +
                "&articuloId=" + articuloId + "&reparacionId=" + reparacionId + "&tamanioId=" + tamanioId);
    }
}
