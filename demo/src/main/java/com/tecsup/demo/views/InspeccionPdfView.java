package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Inspeccion; // Asegúrate de que esta clase existe y tiene los campos que necesitas
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("inspeccion/ver.pdf")
public class InspeccionPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Inspeccion> inspecciones = (List<Inspeccion>) model.get("inspecciones");

        if (inspecciones == null || inspecciones.isEmpty()) {
            document.add(new Phrase("No hay inspecciones disponibles."));
            return;
        }

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Inspecciones"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(5); // Cambia a 5 columnas
        tabla2.addCell("ID");
        tabla2.addCell("ID Coche");
        tabla2.addCell("Fecha");
        tabla2.addCell("Resultado");
        tabla2.addCell("Comentarios");

        for (Inspeccion inspeccion : inspecciones) {
            tabla2.addCell(String.valueOf(inspeccion.getId()));
            tabla2.addCell(String.valueOf(inspeccion.getIdCoche()));
            tabla2.addCell(inspeccion.getFecha().toString());
            tabla2.addCell(inspeccion.getResultado());
            tabla2.addCell(inspeccion.getComentarios());
        }

        document.add(tabla);
        document.add(tabla2);
    }
}
