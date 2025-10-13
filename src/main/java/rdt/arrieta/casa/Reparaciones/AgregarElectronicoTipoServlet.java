package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_electronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarElectronicoTipo")
public class AgregarElectronicoTipoServlet extends HttpServlet {
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
        Query<tipo_electronico> query =
                session.createQuery("FROM tipo_electronico", tipo_electronico.class);
        List<tipo_electronico> tipos = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tipos", tipos);

        request.getRequestDispatcher("agregarElectronicoTipo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        response.sendRedirect(request.getContextPath() + "/agregarElectronicoNombre?clienteId=" + clienteId +
                "&articuloId=" + articuloId + "&reparacionId=" + reparacionId + "&tipoId=" + tipoId);
    }
}
