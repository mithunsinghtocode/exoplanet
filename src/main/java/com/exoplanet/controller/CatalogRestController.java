package com.exoplanet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exoplanet.core.GenerateCatalog;
import com.exoplanet.model.Exoplanet;
import com.exoplanet.utils.ErrorFormatUtils;
import com.exoplanet.utils.FileUtils;

@RestController
public class CatalogRestController {

	@Autowired
	GenerateCatalog catalog;

	@RequestMapping("/generateCatalog")
	public String generateCatalog(@RequestParam(required = false) String filepath) {
		try {
			ArrayList<String> errorList = new ArrayList<>();
			String forceExit = System.getProperty("force.exit.on.error");
			List<Exoplanet> exoplanets = FileUtils.readFile(filepath, errorList);
			System.out.println("System Param :: "+forceExit);
			if (errorList.size() > 0) {
					return ErrorFormatUtils.errorFormat(errorList);
			}
			return catalog.generateCatalog(exoplanets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Returned empty due to uncatched error...";
	}

}
