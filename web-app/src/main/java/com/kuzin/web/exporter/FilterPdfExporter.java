package com.kuzin.web.exporter;

import com.kuzin.entity.Repair;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/** filter report class.*/
public class FilterPdfExporter {
    private List<Repair> repairList;

    public FilterPdfExporter(List<Repair> repairList) {
        this.repairList = repairList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("id", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) {
        for (Repair repair : repairList) {
            table.addCell(repair.getDescription());
            table.addCell(String.valueOf(repair.getId()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("filter by repair description"));


        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }

}
