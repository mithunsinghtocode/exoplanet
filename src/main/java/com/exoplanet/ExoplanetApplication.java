package com.exoplanet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.exoplanet.core.GenerateCatalog;
import com.exoplanet.model.Exoplanet;
import com.exoplanet.utils.ErrorFormatUtils;
import com.exoplanet.utils.FileUtils;

@SpringBootApplication
public class ExoplanetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ExoplanetApplication.class, args);
		GenerateCatalog genCatalog = context.getBean(GenerateCatalog.class);
		try {
			String filePath = "";
			if(args != null && args.length > 0) {
				for(String ar: args) {
					System.out.println(ar);
					String[] argument = ar.split("=");
					if(argument[0].equals("filepath")) {
						filePath = argument[1];
						if(!filePath.trim().equals("")) {
							ArrayList<String> errorList = new ArrayList<>();
							List<Exoplanet> planetList = FileUtils.readFile(filePath, errorList);
							String forceExit = System.getProperty("force.exit.on.error");
							if (errorList.size() > 0) {
								ErrorFormatUtils.errorFormat(errorList);
							}else {
								
							}
							genCatalog.generateCatalog(planetList);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
