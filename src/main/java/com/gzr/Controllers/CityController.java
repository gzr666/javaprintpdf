package com.gzr.Controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzr.Models.City;
import com.gzr.helpers.GenerateExcel;
import com.gzr.helpers.GeneratePdfReport;
import com.gzr.repos.CityRepository;

@RestController
public class CityController {
	
	
	private CityRepository repo;

	@Autowired
	public CityController(CityRepository repo)
	{
		this.repo = repo;
		
	}
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

        List<City> cities =  (List<City>) repo.findAll();

        ByteArrayInputStream bis = GeneratePdfReport.createPDF(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@RequestMapping(value = "/excelreport", method = RequestMethod.GET,produces="application/vnd.ms-excel")
    public ResponseEntity<InputStreamResource> citiesReportXCL() throws IOException {

        List<City> cities =  (List<City>) repo.findAll();

        ByteArrayInputStream bis = GenerateExcel.genEXCEL(cities);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }
	

}
