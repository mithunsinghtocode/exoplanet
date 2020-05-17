package com.exoplanet.core;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.exoplanet.model.Catalog;
import com.exoplanet.model.Config;
import com.exoplanet.model.Exoplanet;

@Component
public class PlanetPropertyHelper implements Helper {

	@Override
	public <T> Catalog evaluate(List<Exoplanet> planetList, Config configParams, Catalog catalog) {

		List<Exoplanet> planetOrbitingHottestStar = (List<Exoplanet>) planetList.stream()
				.filter(in -> ((Exoplanet) in).getHostStarTempK() == planetList.stream()
						.max(Comparator.comparing(Exoplanet::getHostStarTempK)).get().getHostStarTempK())
				.collect(Collectors.toList());

		catalog.setPlanetOrbitingHottestStar(
				planetOrbitingHottestStar.stream().map(in -> in.getPlanetIdentifier()).collect(Collectors.joining()));
		return catalog;
	}

}
