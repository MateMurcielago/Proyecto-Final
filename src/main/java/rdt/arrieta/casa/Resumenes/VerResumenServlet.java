package rdt.arrieta.casa.Resumenes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import rdt.arrieta.casa.clases.ResumenMesReparacion;
import rdt.arrieta.casa.clases.ResumenMesRepuesto;
import rdt.arrieta.casa.db.DBManager;
import java.io.*;
import java.util.List;

@WebServlet("/verResumen")
public class VerResumenServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if(sessionP ==  null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int anio = Integer.parseInt(request.getParameter("anio"));
        int mes = Integer.parseInt(request.getParameter("mes"));
        int mesReal = mes + 1;
        String mesStr = switch (mes) {
            case 0 -> "Enero";
            case 1 -> "Febrero";
            case 2 -> "Marzo";
            case 3 -> "Abril";
            case 4 -> "Mayo";
            case 5 -> "Junio";
            case 6 -> "Julio";
            case 7 -> "Agosto";
            case 8 -> "Septiembre";
            case 9 -> "Octubre";
            case 10 -> "Noviembre";
            case 11 -> "Diciembre";
            default -> "Batman";
        };

        Session session = DBManager.getSessionFactory().openSession();

        Query<ResumenMesReparacion> query1 = session.createQuery(
                "FROM ResumenMesReparacion r WHERE r.detalle.anio = :anio AND r.detalle.mes = :mes",
                        ResumenMesReparacion.class)
                .setParameter("anio", anio).setParameter("mes", mesReal);
        List<ResumenMesReparacion> resumenesReparaciones = query1.getResultList();

        Query<ResumenMesRepuesto> query2 = session.createQuery(
                "FROM ResumenMesRepuesto r WHERE r.detalle.anio = :anio AND r.detalle.mes = :mes",
                        ResumenMesRepuesto.class)
                .setParameter("anio", anio).setParameter("mes", mesReal);
        List<ResumenMesRepuesto> resumenesRepuestos = query2.getResultList();

        session.close();

        Double total = 0.0;

        for (ResumenMesReparacion r : resumenesReparaciones) {
            total += r.getDetalle().getPrecio();
        }

        for (ResumenMesRepuesto r : resumenesRepuestos) {
            total -= r.getDetalle().getPrecio();
        }

        request.setAttribute("mes", mesStr);
        request.setAttribute("anio", anio);
        request.setAttribute("resumenesReparaciones", resumenesReparaciones);
        request.setAttribute("resumenesRepuestos", resumenesRepuestos);
        request.setAttribute("total", total);

        request.getRequestDispatcher("verResumen.jsp").forward(request, response);
    }
}
