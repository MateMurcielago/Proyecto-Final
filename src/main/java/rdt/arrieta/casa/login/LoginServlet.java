package rdt.arrieta.casa.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Properties usuarios = new Properties();

    @Override
    public void init() throws ServletException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("usuarios.properties")) {
            if (input == null) {
                throw new ServletException("No se encontró el archivo usuarios.properties");
            }
            usuarios.load(input);
        } catch (IOException e) {
            throw new ServletException("Error al cargar usuarios.properties", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("usuario");
        String pass = request.getParameter("password");

        // Validar contra el archivo de propiedades
        String passwordAlmacenada = usuarios.getProperty(user);

        if (passwordAlmacenada != null && BCrypt.checkpw(pass, passwordAlmacenada)) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", user);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
