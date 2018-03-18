package com.gzr.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.gzr.Models.City;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {
	
	public static ByteArrayInputStream createPDF(List<City> cities)
	{
		Document doc = new Document(PageSize.A4,36, 36, 90, 36);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer ;
	
		
		
		  
		  
		
		try
		{
			
		//dodavanje slike
			//Image img = Image.getInstance("classpath:/static/giger.png");
			//img.scaleAbsolute(PageSize.A4_LANDSCAPE);
			//img.scaleToFit(PageSize.A4_LANDSCAPE);
			
			/*  Font f = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);
		        Chunk c = new Chunk("IZVJEŠTAJ", f);
		        c.setBackground(BaseColor.RED);
		        Paragraph p = new Paragraph(c);
		        p.setAlignment(Element.ALIGN_CENTER);
		        p.setSpacingAfter(30f);*/
		        
		       
			
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(80);
        table.setWidths(new int[]{1, 3, 3});
        
        
        
        
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        
        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Id", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Name", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Population", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(hcell);
        
        for (City city : cities) {

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(city.getId().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(city.getName()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingRight(5);
            table.addCell(cell);
        }
        
        
        
        writer = PdfWriter.getInstance(doc, out);
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
       
        
        doc.open();
        //doc.add(img);
        //doc.add(p);
       
        doc.add(table);
        Paragraph p = new Paragraph();
       
        
       // doc.add(p);
        
        doc.close();
        
		}
		catch(DocumentException ex){
			
		}
		
		 return new ByteArrayInputStream(out.toByteArray());
		
		
	}

}
