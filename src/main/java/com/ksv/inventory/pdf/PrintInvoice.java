package com.ksv.inventory.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ksv.inventory.entity.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrintInvoice {

      private final ObservableList<Item> items;
      private final String barcode;
      private double due;
      public PrintInvoice(ObservableList<Item> items, String barcode,double due) {
         this.items = FXCollections.observableArrayList(items);
         this.barcode = barcode;
         this.due = due;
      }
	public void generateReport() throws IOException {
		
		/*fetch the path and add date and time */
		String path = System.getProperty("user.name");
    	LocalDateTime date = LocalDateTime.now();
    	DateTimeFormatter dateAndTime = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    	String filename = date.format(dateAndTime);

        try {
        	
        	FileOutputStream fs = new FileOutputStream("C:\\Users\\"+path+"\\Downloads\\report"+filename+".pdf");
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            Paragraph paragraph = new Paragraph("Product ID");
            document.add(paragraph);
            addEmptyLine(paragraph, 5);

            PdfContentByte cb = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCodeType(codeEAN.EAN13);
            codeEAN.setCode(barcode);
            document.add(codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.DARK_GRAY));
            addEmptyLine(paragraph, 5);

            PdfPTable table = createTable();
            document.add(table);

            document.close();
            fs.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            
        }
    }
	
	Item I = new Item();
	
    private PdfPTable createTable() {

        PdfPTable table = new PdfPTable(5);
        PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Due Amount"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        
        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Item i : items) {
            table.addCell(i.getItemName());
            table.addCell(String.valueOf(i.getUnitPrice()));
            table.addCell(String.valueOf(i.getQuantity()));
            table.addCell(String.valueOf(due));
            table.addCell(String.valueOf(i.getTotal()));
        }

        return table;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
