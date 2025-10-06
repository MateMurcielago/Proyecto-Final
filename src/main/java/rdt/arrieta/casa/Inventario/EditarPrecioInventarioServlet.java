package rdt.arrieta.casa.Inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import rdt.arrieta.casa.clases.Inventario_electronico;
import rdt.arrieta.casa.clases.Inventario_pantalla;
import rdt.arrieta.casa.clases.Inventario_led;
import rdt.arrieta.casa.clases.embebidas.id_inventario_electronico;
import rdt.arrieta.casa.clases.embebidas.id_inventario_pantalla;
import rdt.arrieta.casa.clases.embebidas.id_inventario_led;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/editarPrecioInventario")
public class EditarPrecioInventarioServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //login
        HttpSession sessionP = request.getSession(false);
        if (sessionP == null || sessionP.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String tipo = request.getParameter("tipo");
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        Long inventarioId = Long.parseLong(request.getParameter("inventarioId"));
        Double precio = Double.parseDouble(request.getParameter("precio"));

        request.setAttribute("tipo", tipo);
        request.setAttribute("repuestoId", repuestoId);
        request.setAttribute("inventarioId", inventarioId);
        request.setAttribute("precio", precio);
        request.getRequestDispatcher("editarPrecioInventario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        Long repuestoId = Long.parseLong(request.getParameter("repuestoId"));
        Long inventarioId = Long.parseLong(request.getParameter("inventarioId"));
        Double precio = Double.parseDouble(request.getParameter("precio"));

        String paginaAnterior = "";

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        switch(tipo) {
            case "electronico":
                id_inventario_electronico ide = new id_inventario_electronico(inventarioId, repuestoId);
                Inventario_electronico e = session.get(Inventario_electronico.class, ide);
                e.getInventario().setPrecio(precio);
                paginaAnterior = "verInventarioElectronico";
                break;
            case "pantalla":
                id_inventario_pantalla idp = new id_inventario_pantalla(inventarioId, repuestoId);
                Inventario_pantalla p = session.get(Inventario_pantalla.class, idp);
                p.getInventario().setPrecio(precio);
                paginaAnterior = "verInventarioPantalla";
                break;
            case "led":
                id_inventario_led idl = new id_inventario_led(inventarioId, repuestoId);
                Inventario_led l = session.get(Inventario_led.class, idl);
                l.getInventario().setPrecio(precio);
                paginaAnterior = "verInventarioLed";
                break;
        }

        session.getTransaction().commit();
        session.close();

        response.sendRedirect(paginaAnterior);
    }
}
