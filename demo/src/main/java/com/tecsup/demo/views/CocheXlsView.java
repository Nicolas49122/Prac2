package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Coche; // Asegúrate de que esta clase existe y tiene los campos que necesitas
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

@Component("coche/ver.xlsx")
public class CocheXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"coche_view.xlsx\"");
        List<Coche> coches = (List<Coche>) model.get("coches");
        Sheet sheet = workbook.createSheet("Lista de Coches");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Coches");

        row = sheet.createRow(1);

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Modelo");
        header.createCell(2).setCellValue("Año");
        header.createCell(3).setCellValue("Precio");
        header.createCell(4).setCellValue("Kilometraje");
        header.createCell(5).setCellValue("Estado");
        header.createCell(6).setCellValue("Descripción");
        header.createCell(7).setCellValue("Fecha de Publicación");

        for (int i = 0; i <= 7; i++) {
            header.getCell(i).setCellStyle(theaderStyle);
        }

        int rownum = 6;

        for (Coche coche : coches) {
            Row fila = sheet.createRow(rownum++);
            cell = fila.createCell(0);
            cell.setCellValue(coche.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(coche.getModelo());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(coche.getAno());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(coche.getPrecio());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(coche.getKilometraje());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(5);
            cell.setCellValue(coche.getEstado());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(6);
            cell.setCellValue(coche.getDescripcion());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(7);
            cell.setCellValue(coche.getFechaPublicacion().toString());
            cell.setCellStyle(tbodyStyle);
        }
    }
}
