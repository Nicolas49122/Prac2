package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Coche; // Asegúrate de que esta clase existe y tiene los campos que necesitas
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("coche/ver.pdf")
public class CochePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Coche> coches = (List<Coche>) model.get("coches");

        if (coches == null || coches.isEmpty()) {
            document.add(new Phrase("No hay coches disponibles."));
            return;
        }

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Coches"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(8); // Cambia a 8 columnas
        tabla2.addCell("ID");
        tabla2.addCell("Modelo");
        tabla2.addCell("Año");
        tabla2.addCell("Precio");
        tabla2.addCell("Kilometraje");
        tabla2.addCell("Estado");
        tabla2.addCell("Descripción");
        tabla2.addCell("Fecha de Publicación");

        for (Coche coche : coches) {
            tabla2.addCell(String.valueOf(coche.getId()));
            tabla2.addCell(coche.getModelo());
            tabla2.addCell(String.valueOf(coche.getAno()));
            tabla2.addCell(String.valueOf(coche.getPrecio()));
            tabla2.addCell(String.valueOf(coche.getKilometraje()));
            tabla2.addCell(coche.getEstado());
            tabla2.addCell(coche.getDescripcion());
            tabla2.addCell(coche.getFechaPublicacion().toString());
        }

        document.add(tabla);
        document.add(tabla2);
    }
}
