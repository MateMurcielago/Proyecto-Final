package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.FallaComun;
import rdt.arrieta.casa.clases.marca_articulo;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.clases.tipo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/editarFallaComun")
public class EditarFallaComunServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long fallaId = Long.parseLong(request.getParameter("fallaId"));

        Session session = DBManager.getSessionFactory().openSession();
        FallaComun fallaComun = session.get(FallaComun.class, fallaId);
        session.close();

        request.setAttribute("fallaId", fallaId);
        request.setAttribute("fallaComun", fallaComun);

        request.getRequestDispatcher("editarFallaComun.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long fallaId = Long.parseLong(request.getParameter("fallaId"));
        String resumen = request.getParameter("resumen");
        String falla = request.getParameter("falla");
        String solucion = request.getParameter("solucion");

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        FallaComun f = session.get(FallaComun.class, fallaId);
        f.setResumen(resumen);
        f.setFalla(falla);
        f.setSolucion(solucion);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verFallaComun?fallaId=" + fallaId);
    }
}
