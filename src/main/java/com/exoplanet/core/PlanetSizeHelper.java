package com.exoplanet.core;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.exoplanet.constants.Condition;
import com.exoplanet.model.Catalog;
import com.exoplanet.model.Config;
import com.exoplanet.model.Exoplanet;

@Component
public class PlanetSizeHelper implements Helper {

	@Override
	public <T> Catalog evaluate(List<Exoplanet> planetList, Config configParams, Catalog catalog) {
		short year = (short) configParams.getValue();
		int smallPlanets;
		int mediumPlanets;
		int bigPlanets;

		smallPlanets = getPlanetsBasedOnSize.apply(planetList, Config.createConfig(1, Condition.LESS_THAN)).size();
		List<Exoplanet> mediumPlannetsTempCond = getPlanetsBasedOnSize.apply(planetList,
				Config.createConfig(2, Condition.LESS_THAN));
		mediumPlanets = getPlanetsBasedOnSize
				.apply(mediumPlannetsTempCond, Config.createConfig(1, Condition.GREATER_THAN)).size();
		bigPlanets = getPlanetsBasedOnSize.apply(planetList, Config.createConfig(2, Condition.GREATER_THAN)).size();

		String resultString = catalog.formatPlanetDiscoverYearDetails(year, smallPlanets, mediumPlanets, bigPlanets);
		catalog.getPlanetsDiscoveredPerYearDetails().add(resultString);

		return catalog;
	}

	BiFunction<List<Exoplanet>, Config, List<Exoplanet>> getPlanetsBasedOnSize = (inList, config) -> {
		return inList.stream()
				.filter((in) -> config.getCondition().equals(Condition.GREATER_THAN)
						? in.getRadiusJpt() > config.getValue()
						: in.getRadiusJpt() < config.getValue())
				.collect(Collectors.toList());
	};
}
