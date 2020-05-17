package com.exoplanet.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.exoplanet.model.Exoplanet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils {

	public static List<Exoplanet> readFile(String filePath, ArrayList<String> errorList) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Exoplanet> planetList = null;
		List<Exoplanet> exoplanets = null;
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			if(filePath==null || filePath.equals("")) {
				ClassPathResource cpr = new ClassPathResource("json/exoplanet.json");
				 byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
				    String data = new String(bdata, StandardCharsets.UTF_8);
				    exoplanets = Arrays.asList(objectMapper.readValue(data, Exoplanet[].class));
			}else {
				File file = ResourceUtils.getFile(filePath);
				exoplanets = Arrays.asList(objectMapper.readValue(new FileInputStream(file), Exoplanet[].class));
			}
		planetList = exoplanets.stream().collect(Collectors.toList());
		}catch(IOException e) {
			System.out.println(e.getLocalizedMessage());
			errorList.add("Error in locating File: "+ e.getLocalizedMessage());
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			errorList.add("Error in Parsing File: "+ e.getLocalizedMessage());
		}
		return planetList;	
	}
}
