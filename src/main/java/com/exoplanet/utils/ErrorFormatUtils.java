package com.exoplanet.utils;

import java.util.List;

public class ErrorFormatUtils {

	public static String errorFormat(List<String> errorList) {
		return String.join("\n",
				"------------------ Error Occurred.-------------------- \n Please resolve error before proceeding again.\n\n Error Description below, \n",
				String.join("\n\n", errorList));
	}
}
