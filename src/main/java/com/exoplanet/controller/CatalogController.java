package com.exoplanet.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exoplanet.core.GenerateCatalog;
import com.exoplanet.model.Exoplanet;
import com.exoplanet.utils.ErrorFormatUtils;
import com.exoplanet.utils.FileUtils;

@Controller
public class CatalogController {

	@Autowired
	GenerateCatalog catalog;
	//localhost:8080/generateCatalogFile
	@RequestMapping(value = "/generateCatalogFile", method = RequestMethod.GET)
    public void getFile(@RequestParam(required=false) String filepath, HttpServletResponse response) {
		try {
		System.out.println("filepath :: "+ filepath);
		ArrayList<String> errorList = new ArrayList<>();
		List<Exoplanet> exoplanets = FileUtils.readFile(filepath, errorList);
		String forceExit = System.getProperty("force.exit.on.error");
		InputStream is = null;
		if (errorList.size() > 0) {
			is = new ByteArrayInputStream(ErrorFormatUtils.errorFormat(errorList).getBytes());
		}else {
			is = new ByteArrayInputStream(catalog.generateCatalog(exoplanets).getBytes());
		}
		   // copy it to response’s OutputStream
		   IOUtils.copy(is, response.getOutputStream());
		   String filename = "attachment;" + "filename = "+"catalog";
		   response.setContentType("application / text");
		   response.setHeader("Content - Disposition", "attachment; filename = catalog.txt");
		   response.flushBuffer();
		   System.out.println("Filename————— -> "+filename);	
		}catch(IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
    }	
}
