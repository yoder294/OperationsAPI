package com.operation.api.users.app.util;

import io.micrometer.common.util.StringUtils;

public class ValidationGeneric {

	private static final String REGEX_NUMBER_ONLY = "[0-9]+";

	public static boolean isValidString(String value) {
		return (value != null) && !StringUtils.isBlank(value.trim());
	}

	public static boolean isValidLengthString(String value, Integer length) {
		
		if (!isValidString(value)) {
              return false;
		}
		
		return (value.trim().length() == length);
	}

	public static boolean isValidNumber(Integer value) {
		return (value != null);
	}

	public static boolean isGreathZeroNumber(Integer value) {
		return isValidNumber(value) && value > 0;
	}

	public static boolean isValidNumberLength(Integer value, Integer length) {

		if (!isGreathZeroNumber(value)) {
			return false;
		}

		int nDigits = (int) (Math.floor(Math.log10(Math.abs(value))) + 1);
		return (nDigits == length);
	}
	
	public static boolean isOnlyNumbers(String value) {
		return value.matches(REGEX_NUMBER_ONLY);
	}
	
	public static boolean stringValidLengtOnlyNumber(String value, Integer length) {
		 return isValidLengthString(value, length) && isOnlyNumbers(value);
	}
		
}
