package com.exoplanet.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.exoplanet.model.Catalog;
import com.exoplanet.model.Config;
import com.exoplanet.model.Exoplanet;

@Component
@FunctionalInterface
public interface Helper {

	<T> Catalog evaluate(List<Exoplanet> planetList, Config configParams, Catalog catalog);
}
