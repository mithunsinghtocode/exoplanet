package com.exoplanet.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog {

	private long orphanPlanets;
	private String planetOrbitingHottestStar;
	private List<String> planetsDiscoveredPerYearDetails;

	public String formatPlanetDiscoverYearDetails(short year, int smallPlanets, int mediumPlanets, int bigPlanets) {
		if(year != 0)
		return String.format("In %d we discovered %d small planets, %d medium planets, and %d large planets.", year,
				smallPlanets, mediumPlanets, bigPlanets);
		
		return String.format("Year Unknown :: We discovered %d small planets, %d medium planets, and %d large planets.",
				smallPlanets, mediumPlanets, bigPlanets);
	}
}
