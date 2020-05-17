package com.exoplanet.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exoplanet.constants.ExoplanetType;
import com.exoplanet.model.Catalog;
import com.exoplanet.model.Config;
import com.exoplanet.model.Exoplanet;
import com.exoplanet.utils.CatalogFormatUtils;

@Component
public class GenerateCatalog {

	@Autowired
	PlanetSizeHelper planetSizeHelper;

	@Autowired
	PlanetTypeHelper planetTypeHelper;

	@Autowired
	PlanetPropertyHelper planetPropertyHelper;

	public String generateCatalog(List<Exoplanet> exoplanetList) {
		
		if(exoplanetList != null && exoplanetList.size() > 0) {
		Catalog catalog = new Catalog();
		catalog.setPlanetsDiscoveredPerYearDetails(new ArrayList<>());
		Config configParams = new Config();

		// 1. The number of orphan planets (no star).
		configParams.setValue(ExoplanetType.ORPHAN_PLANETS.getKeyInInteger());
		planetTypeHelper.evaluate(exoplanetList, configParams, catalog);

		// 2. The name (planet identifier) of the planet orbiting the hottest star
		planetPropertyHelper.evaluate(exoplanetList, configParams, catalog);

		/*
		 * 3. A timeline of the number of planets discovered per year grouped by size.
		 * Use the following groups: “small” is less than 1 Jupiter radii “medium” is
		 * less than 2 Jupiter radii anything bigger is considered “large”
		 * 
		 */
		Map<Short, List<Exoplanet>> exoplanetBasedOnYear = exoplanetList.stream()
				.collect(Collectors.groupingBy(Exoplanet::getDiscoveryYear));
		Map<Short, List<Exoplanet>> sortedMapBasedOnExoplanetYear = exoplanetBasedOnYear.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		sortedMapBasedOnExoplanetYear.forEach((yearOfDiscovery, planetList) -> {
			configParams.setValue(yearOfDiscovery);
			planetSizeHelper.evaluate(planetList, configParams, catalog);
		});		
		System.out.println(CatalogFormatUtils.formatString(catalog));
			return CatalogFormatUtils.formatString(catalog);
		}
		return "No data to display... ";
	}
}
