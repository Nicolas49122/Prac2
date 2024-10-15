package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Inspeccion; // Asegúrate de que esta clase existe y tiene los campos que necesitas
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("inspeccion/ver.xlsx")
public class InspeccionXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"inspeccion_view.xlsx\"");
        List<Inspeccion> inspecciones = (List<Inspeccion>) model.get("inspecciones");
        Sheet sheet = workbook.createSheet("Lista de Inspecciones");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Inspecciones");

        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("ID Coche");
        header.createCell(2).setCellValue("Fecha");
        header.createCell(3).setCellValue("Resultado");
        header.createCell(4).setCellValue("Comentarios");

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i <= 4; i++) {
            header.getCell(i).setCellStyle(theaderStyle);
        }

        int rownum = 6;

        for (Inspeccion inspeccion : inspecciones) {
            Row fila = sheet.createRow(rownum++);
            cell = fila.createCell(0);
            cell.setCellValue(inspeccion.getId());

            cell = fila.createCell(1);
            cell.setCellValue(inspeccion.getIdCoche());

            cell = fila.createCell(2);
            cell.setCellValue(inspeccion.getFecha().toString());

            cell = fila.createCell(3);
            cell.setCellValue(inspeccion.getResultado());

            cell = fila.createCell(4);
            cell.setCellValue(inspeccion.getComentarios());
        }
    }
}
