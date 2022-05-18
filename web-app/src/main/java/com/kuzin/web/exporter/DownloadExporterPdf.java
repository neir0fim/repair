package com.kuzin.web.exporter;


import com.kuzin.entity.Report;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/** pdf exporter class.*/
public class DownloadExporterPdf {
    private final Report fail;

    public DownloadExporterPdf(Report fail) {
        this.fail = fail;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

    }


    private void writeTableData(PdfPTable table) {
        for (Integer row : fail.getFailRow()) {
            table.addCell(row.toString());

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph("REPORT"));
        document.add(new Paragraph("success case: " + fail.getSuccess()));
        document.add(new Paragraph("fail case: " + fail.getFail()));
        document.add(new Paragraph("lines that were not written"));

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.add(new Paragraph("the download may have failed:"));

        document.add(new Paragraph("if such data already exists or the data in"
                + " the file was incorrect"));
        document.add(new Paragraph("Check it out and try again"));
        document.close();
    }

}
