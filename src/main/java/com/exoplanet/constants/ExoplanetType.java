package com.exoplanet.constants;

public enum ExoplanetType implements EnumType<ExoplanetType> {

	NO_BINARY_COMPANION("0"), P_TYPE_BINARY("1"), S_TYPE_BINARY("2"), ORPHAN_PLANETS("3");

	private String key;
	private String value;

	/**
	 * Constructor
	 */
	private ExoplanetType(String key) {
		this.key = key;
	}

	/**
	 * @return key String
	 */
	public String getKey() {
		return key;
	}

	public int getKeyInInteger() {
		return Integer.parseInt(key);
	}

	/**
	 * @param key String - key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public final static ExoplanetType getInstance(String key) {
		for (ExoplanetType element : values()) {
			if (element.getKey().equals(key)) {
				return element;
			}
		}
		return null;
	}
}
