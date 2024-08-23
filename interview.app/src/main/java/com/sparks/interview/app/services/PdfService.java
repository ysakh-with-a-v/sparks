package com.sparks.interview.app.services;


import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sparks.interview.app.models.Product;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfService {


    public void export(HttpServletResponse response,List<Product> products) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Products", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table,products);

        document.add(table);

        document.close();

    }

        private void writeTableHeader(PdfPTable table) {
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.BLUE);
            cell.setPadding(5);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(Color.WHITE);

            cell.setPhrase(new Phrase("product_id", font));

            table.addCell(cell);

            cell.setPhrase(new Phrase("description", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("name", font));
            table.addCell(cell);

            cell.setPhrase(new Phrase("price", font));
            table.addCell(cell);
        }

        private void writeTableData(PdfPTable table, List<Product> products) {
            for (Product product : products) {
                table.addCell(String.valueOf(product.getProduct_id()));
                table.addCell(product.getDescription());
                table.addCell(product.getName());
                table.addCell(String.valueOf(product.getPrice()));
            }
        }
}
