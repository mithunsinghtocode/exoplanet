package com.exoplanet.utils;

import com.exoplanet.model.Catalog;

public class CatalogFormatUtils {
	public static String formatString( Catalog catalog){
		return String.join("\n"
				,"-------------------------------Exoplanet Catalog--------------------------------------------------------"
				, "\nThe number of orphan planets (no star) : " +catalog.getOrphanPlanets()
				,"\n---------------------------------------------------------------------------------------------------------"
				, "\nThe name (planet identifier) of the planet orbiting the hottest star : " + catalog.getPlanetOrbitingHottestStar()
				,"\n---------------------------------------------------------------------------------------------------------\n"
				, String.join("\n\n",catalog.getPlanetsDiscoveredPerYearDetails())
				,"\n---------------------------------------------------------------------------------------------------------");
	}

}
