package com.exoplanet.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.exoplanet.model.Catalog;
import com.exoplanet.model.Config;
import com.exoplanet.model.Exoplanet;

@Component
public class PlanetTypeHelper implements Helper {

	@Override
	public <T> Catalog evaluate(List<Exoplanet> planetList, Config configParams, Catalog catalog) {
		
				// 1. The number of Exoplanet Type planets (no star).
				long numOfPlanets = planetList.stream().filter(in -> ((Exoplanet) in).getTypeFlag() == configParams.getValue()).count();
				catalog.setOrphanPlanets(numOfPlanets);
				
		return catalog;
	}
}
