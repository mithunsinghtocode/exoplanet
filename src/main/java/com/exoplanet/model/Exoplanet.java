package com.exoplanet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Exoplanet {
	@JsonProperty("PlanetIdentifier")
	private String planetIdentifier;
	@JsonProperty("TypeFlag")
	private byte typeFlag;
	@JsonProperty("PlanetaryMassJpt")
	private String planetaryMassJpt;
	@JsonProperty("RadiusJpt")
	private double radiusJpt;
	@JsonProperty("PeriodDays")
	private double periodDays;
	@JsonProperty("SemiMajorAxisAU")
	private double semiMajorAxisAU;
	@JsonProperty("Eccentricity")
	private double eccentricity;
	@JsonProperty("PeriastronDeg")
	private double periastronDeg;
	@JsonProperty("LongitudeDeg")
	private double longitudeDeg;
	@JsonProperty("AscendingNodeDeg")
	private double ascendingNodeDeg;
	@JsonProperty("InclinationDeg")
	private double inclinationDeg;
	@JsonProperty("SurfaceTempK")
	private double surfaceTempK;
	@JsonProperty("AgeGyr")
	private double ageGyr;
	@JsonProperty("DiscoveryMethod")
	private String discoveryMethod;
	@JsonProperty("DiscoveryYear")
	private short discoveryYear;
	@JsonProperty("LastUpdated")
	private String lastUpdated;
	@JsonProperty("RightAscension")
	private String rightAscension;
	@JsonProperty("Declination")
	private String declination;
	@JsonProperty("DistFromSunParsec")
	private double distFromSunParsec;
	@JsonProperty("HostStarMassSlrMass")
	private double hostStarMassSlrMass;
	@JsonProperty("HostStarRadiusSlrRad")
	private double hostStarRadiusSlrRad;
	@JsonProperty("HostStarMetallicity")
	private double hostStarMetallicity;
	@JsonProperty("HostStarTempK")
	private double hostStarTempK;
	@JsonProperty("HostStarAgeGyr")
	private double hostStarAgeGyr;
}