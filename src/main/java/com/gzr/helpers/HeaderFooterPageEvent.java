package com.gzr.helpers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
	
	private PdfTemplate t;
	private Image total;
	
	
	public void onOpenDocument(PdfWriter writer,Document document)
	{
		t = writer.getDirectContent().createTemplate(30,19);
	
		try {
			total = Image.getInstance(t);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		total.setRole(PdfName.ARTIFACT);
		
	}
	
	@Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
			addHeader(writer);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        addFooter(writer);
       // addFooter(writer);
    }
	
	private void addHeader(PdfWriter writer) throws MalformedURLException, IOException{
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{2, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
            Image logo = Image.getInstance("classpath:/static/bmw2.png");
            header.addCell(logo);

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            
           
            text.addElement(new Phrase("iText PDF Header Footer Example", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("https://memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);
            

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
	
	 private void addFooter(PdfWriter writer){
	        PdfPTable footer = new PdfPTable(3);
	        try {
	            // set defaults
	        	//footer.setSpacingBefore(30);
	            footer.setWidths(new int[]{24, 2, 1});
	            footer.setTotalWidth(527);
	            footer.setLockedWidth(true);
	            footer.getDefaultCell().setPaddingTop(5);
	            footer.getDefaultCell().setFixedHeight(34);
	            footer.getDefaultCell().setBorder(Rectangle.TOP);
	            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

	            // add copyright
	            footer.addCell(new Phrase("\u00A9 Memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

	            // add current page count
	            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            footer.addCell(new Phrase(String.format("Page %d of ", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

	            // add placeholder for total page count
	            PdfPCell totalPageCount = new PdfPCell(total);
	            totalPageCount.setBorder(Rectangle.TOP);
	            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
	            totalPageCount.setPaddingLeft(2);
	            footer.addCell(totalPageCount);

	            // write page
	            PdfContentByte canvas = writer.getDirectContent();
	            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
	            footer.writeSelectedRows(0, -1, 34, 34, canvas);
	            canvas.endMarkedContentSequence();
	        } catch(DocumentException de) {
	            throw new ExceptionConverter(de);
	        }
	    }
	
	 public void onCloseDocument(PdfWriter writer, Document document) {
	        int totalLength = String.valueOf(writer.getPageNumber()).length();
	        int totalWidth = totalLength * 5;
	        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
	                new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
	                totalWidth, 6, 0);
	    }

}
