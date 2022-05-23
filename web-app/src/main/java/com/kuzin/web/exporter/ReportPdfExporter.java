package com.kuzin.web.exporter;

import com.kuzin.entity.WorksMaterial;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/** repair report class.*/
public class ReportPdfExporter {
    private final List<WorksMaterial> worksMaterialList;

    public ReportPdfExporter(List<WorksMaterial> worksMaterialList) {
        this.worksMaterialList = worksMaterialList;
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("codDk", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("uom", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("value", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("amount", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("cost", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) {
        for (WorksMaterial worksMaterial : worksMaterialList) {
            table.addCell(worksMaterial.getCodDk());
            table.addCell(worksMaterial.getName());
            table.addCell(worksMaterial.getUom());
            table.addCell(String.valueOf(worksMaterial.getValue()));
            table.addCell(String.valueOf(worksMaterial.getAmount()));
            table.addCell(String.valueOf(worksMaterial.getCost()));
        }
    }

    public void export(HttpServletResponse response, String article) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();


        document.add(new Paragraph("APPROVE\n Chief Engineer\n ___________________"));
        document.add(new Paragraph("REPORT\n for article:" + article));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        double sum = worksMaterialList.stream().mapToDouble(
                WorksMaterial::getCost).sum();

        document.add(new Paragraph("required amount of funding for the article:" + sum));
        document.add(new Paragraph("head of department ___________________________________"));

        document.close();
    }

}
