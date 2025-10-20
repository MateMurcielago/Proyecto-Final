package rdt.arrieta.casa.Herramientas;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import rdt.arrieta.casa.clases.ResumenMesReparacion;
import rdt.arrieta.casa.clases.ResumenMesRepuesto;
import rdt.arrieta.casa.db.DBManager;

import java.io.IOException;
import java.util.List;

@WebServlet("/descargarResumen")
public class ResumenPDFServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int anio = Integer.parseInt(request.getParameter("anio"));
        String mesStr = request.getParameter("mes");
        Double total = Double.parseDouble(request.getParameter("total"));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Resumen_" + mesStr + "_" + anio +
                ".pdf");

        int mes = switch(mesStr) {
            case "Enero"      -> 1;
            case "Febrero"    -> 2;
            case "Marzo"      -> 3;
            case "Abril"      -> 4;
            case "Mayo"       -> 5;
            case "Junio"      -> 6;
            case "Julio"      -> 7;
            case "Agosto"     -> 8;
            case "Septiembre" -> 9;
            case "Octubre"    -> 10;
            case "Noviembre"  -> 11;
            case "Diciembre"  -> 12;
            default -> 1998;
        };

        Session session = DBManager.getSessionFactory().openSession();

        Query<ResumenMesReparacion> query1 = session.createQuery(
                        "FROM ResumenMesReparacion r WHERE r.detalle.anio = :anio AND r.detalle.mes = :mes",
                        ResumenMesReparacion.class)
                .setParameter("anio", anio).setParameter("mes", mes);
        List<ResumenMesReparacion> resumenesReparaciones = query1.getResultList();

        Query<ResumenMesRepuesto> query2 = session.createQuery(
                        "FROM ResumenMesRepuesto r WHERE r.detalle.anio = :anio AND r.detalle.mes = :mes",
                        ResumenMesRepuesto.class)
                .setParameter("anio", anio).setParameter("mes", mes);
        List<ResumenMesRepuesto> resumenesRepuestos = query2.getResultList();

        session.close();

        try{
            //Crear documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Resúmen de " + mesStr + " del " + anio + " super profesional " +
                    "y hermosamente diseñado"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Reparaciones cobradas:"));
            for (ResumenMesReparacion r : resumenesReparaciones) {
                document.add(new Paragraph("    " + r.getDetalle().getDescripcion() + ": $" +
                        r.getDetalle().getPrecio()));
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Repuestos comprados:"));
            for (ResumenMesRepuesto r : resumenesRepuestos) {
                document.add(new Paragraph("    " + r.getDetalle().getDescripcion() + ": $" +
                        r.getDetalle().getPrecio()));
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total: $" + total));

            document.close();
        } catch (Exception e) {
            throw new ServletException("Error al generar el PDF", e);
        }
    }
}
