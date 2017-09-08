package util;

import DatabaseService.entities.DatabaseService;
import DatabaseService.entities.entities.Card;
import DatabaseService.entities.entities.Cardholder;
import DatabaseService.entities.entities.Pitem;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by sergey on 5.5.17.
 */
public class PdfHelper {

    private DatabaseService service;
    private List<Pitem> list;

    public PdfHelper(DatabaseService service) {
        this.service = service;
        this.list = service.findAll(Pitem.class);
    }

    public void createPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
        document.open();
        setContent(document);
        document.close();
    }

    private void setContent(Document document) {
        try {
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Purchases made in stores",titleFont);
            Paragraph result = new Paragraph("Total profit: "+getTotalProfit().toString());
            title.setIndentationLeft(25);
            result.setIndentationLeft(25);
            document.add(title);
            document.add(getTable());
            document.add(result);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private PdfPTable getTable() {

        PdfPTable table = new PdfPTable(5);
        float[] columnWidths = {3f,4f,2f,2f,1f};
        try {
            table.setWidths(columnWidths);
            table.addCell("Artist");
            table.addCell("Album");
            table.addCell("Amount");
            table.addCell("Date");
            table.addCell("Price");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        table.setSpacingBefore(20f);
        table.setSpacingAfter(10f);
        for(Pitem pitem : list) {
            PdfPCell artistCell = new PdfPCell(new Paragraph(pitem.getLitem().getRecord().getArtist()));
            PdfPCell albumCell = new PdfPCell(new Paragraph(pitem.getLitem().getRecord().getAlbum()));
            PdfPCell amountCell = new PdfPCell(new Paragraph(pitem.getAmount()));
            PdfPCell dateCell = new PdfPCell(new Paragraph(pitem.getPurchase().getDate()));
            Integer purchasePrice = 0;
            if(service.findById(Cardholder.class,pitem.getPurchase().getCustomer().getId())!=null) {
                Cardholder cardholder = (Cardholder) service.findById(Cardholder.class, pitem.getPurchase().getCustomer().getId());
                purchasePrice = (int)(Integer.valueOf(pitem.getLitem().getRecord().getPrice()) *
                        Integer.valueOf(pitem.getAmount()) / 100.0 * (100 - cardholder.getCard().getDiscount()));
            }
            else{
            purchasePrice = Integer.valueOf(pitem.getLitem().getRecord().getPrice()) *
                    Integer.valueOf(pitem.getAmount());
            }
            PdfPCell priceCell = new PdfPCell(new Paragraph(purchasePrice.toString()));

            table.addCell(artistCell);
            table.addCell(albumCell);
            table.addCell(amountCell);
            table.addCell(dateCell);
            table.addCell(priceCell);
        }

        return table;
    }

    private Integer getTotalProfit() {
        Integer totalPrice = 0;
        for(Pitem pitem : list) {
            if(service.findById(Cardholder.class,pitem.getPurchase().getCustomer().getId())!=null) {
                Cardholder cardholder = (Cardholder) service.findById(Cardholder.class, pitem.getPurchase().getCustomer().getId());
                totalPrice += Integer.valueOf(pitem.getLitem().getRecord().getPrice()) *
                        Integer.valueOf(pitem.getAmount()) / 100 * (100 - cardholder.getCard().getDiscount());
            }
            else {
                totalPrice += Integer.valueOf(pitem.getAmount()) *
                        Integer.valueOf(pitem.getLitem().getRecord().getPrice());
            }
        }
        return totalPrice;
    }

}
