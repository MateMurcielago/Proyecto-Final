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

@WebServlet("/editarCliente")
public class EditarClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Long clienteId = Long.parseLong(request.getParameter("idCliente"));

        Session session = DBManager.getSessionFactory().openSession();

        //Busca el cliente
        Cliente cliente = session.get(Cliente.class, clienteId);

        session.close();

        request.setAttribute("cliente", cliente);

        request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long clienteId = Long.parseLong(request.getParameter("idCliente"));

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Long numer_telefono = Long.parseLong(request.getParameter("numero_telefono"));
        String calle = request.getParameter("calle");
        String numero_casa = request.getParameter("numero_casa");
        String ciudad = request.getParameter("ciudad");

        Session session = DBManager.getSessionFactory().openSession();
        session.beginTransaction();

        Cliente c = session.get(Cliente.class, clienteId);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setNumero_telefono(numer_telefono);
        c.setCalle(calle);
        c.setNumero_casa(numero_casa);
        c.setCiudad(ciudad);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("verCliente?id=" + clienteId);
    }
}
