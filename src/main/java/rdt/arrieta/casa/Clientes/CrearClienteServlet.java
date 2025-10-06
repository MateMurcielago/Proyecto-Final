package rdt.arrieta.casa.Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import rdt.arrieta.casa.clases.intermedias.ArticuloCliente;
import rdt.arrieta.casa.clases.Cliente;
import rdt.arrieta.casa.db.DBManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@WebServlet("/crearCliente")
public class CrearClienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Long numero_telefono = Long.parseLong(request.getParameter("numero_telefono"));
        String calle = request.getParameter("calle");
        String numero_casa = request.getParameter("numero_casa");
        String ciudad = request.getParameter("ciudad");

        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setNumero_telefono(numero_telefono);
        c.setCalle(calle);
        c.setNumero_casa(numero_casa);
        c.setCiudad(ciudad);
        c.setArticulos(new ArrayList<>());

        try(Session session = DBManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(c);
            session.getTransaction().commit();
            session.close();
        }

        response.sendRedirect("verCliente?id=" + c.getId());
    }
}
