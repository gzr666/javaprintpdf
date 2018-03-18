package com.gzr.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.gzr.Models.City;

public class GenerateExcel {
	
	public static ByteArrayInputStream genEXCEL(List<City> cities)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet("mysheet");
		
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("POPULATION");
		
		
		//ITERARE CITIES
		
		int rownum = 1;
		Row iterator;
		for(City city : cities)
		{
			iterator = sheet.createRow(rownum++);
			iterator.createCell(0).setCellValue(city.getId());
			iterator.createCell(1).setCellValue(city.getName());
			iterator.createCell(2).setCellValue(city.getPopulation());
			
			
			
		}
		
		try {
			workbook.write(out);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(out.toByteArray());
		
		
		
	}

}
