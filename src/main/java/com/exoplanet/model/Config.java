package com.exoplanet.model;

import com.exoplanet.constants.Condition;

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
public class Config{
	private int value;
	private Condition condition;
	
	public static Config createConfig(int value, Condition cond){
		Config configProps = new Config();
		configProps.setValue(value);;
		configProps.setCondition(cond);
		return configProps;
	}
}