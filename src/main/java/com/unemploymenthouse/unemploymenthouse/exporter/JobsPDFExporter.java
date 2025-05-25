package com.unemploymenthouse.unemploymenthouse.exporter;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unemploymenthouse.unemploymenthouse.domain.Jobs;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class JobsPDFExporter {
    private final List<Jobs> listMaxSalary;

    public JobsPDFExporter(List<Jobs> listMaxSalary) {
        this.listMaxSalary = listMaxSalary;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Job", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Salary", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(Jobs job : listMaxSalary){
            table.addCell(job.getJobTitle());
            table.addCell(String.valueOf(job.getSalary()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        Paragraph title = new Paragraph("List of biggest salary for each specialty", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] {3.5f, 1.5f});


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
