package rdt.arrieta.casa.Reparaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.tipo_electronico;
import rdt.arrieta.casa.clases.nombre_electronico;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/agregarElectronicoNombre")
public class AgregarElectronicoNombreServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));

        Long tipoId = Long.parseLong(request.getParameter("tipoId"));

        Session session = DBManager.getSessionFactory().openSession();
        String tipo = session.get(tipo_electronico.class, tipoId).getTipo();
        Query<nombre_electronico> query = session.createQuery(
                "FROM nombre_electronico n WHERE n.tipo.id = :id", nombre_electronico.class)
                .setParameter("id", tipoId);
        List<nombre_electronico> nombres = query.list();
        session.close();

        request.setAttribute("clienteId", clienteId);
        request.setAttribute("articuloId", articuloId);
        request.setAttribute("reparacionId", reparacionId);
        request.setAttribute("tipoId", tipoId);
        request.setAttribute("tipo", tipo);
        request.setAttribute("nombres", nombres);

        request.getRequestDispatcher("agregarElectronicoNombre.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        Long articuloId = Long.parseLong(request.getParameter("articuloId"));
        Long reparacionId = Long.parseLong(request.getParameter("reparacionId"));
        String tipoId = request.getParameter("tipoId");
        Long nombreId = Long.parseLong(request.getParameter("nombreId"));

        response.sendRedirect(request.getContextPath() + "/agregarElectronicoCodigo?clienteId=" + clienteId +
                "&articuloId=" + articuloId + "&reparacionId=" + reparacionId + "&tipoId=" + tipoId + "&nombreId=" +
                nombreId);
    }
}
