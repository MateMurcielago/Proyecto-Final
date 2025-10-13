package rdt.arrieta.casa.Fallas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.FallaComun;
import rdt.arrieta.casa.clases.modelo_articulo;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/verFallaComun")
public class VerFallaComunServlet extends HttpServlet {
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

        FallaComun falla = session.get(FallaComun.class, fallaId);

        session.close();

        request.setAttribute("fallaId", fallaId);
        request.setAttribute("falla", falla);
    }
}
