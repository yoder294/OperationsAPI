package com.operation.api.users.app.util.enums;

import java.util.Arrays;
import java.util.List;

public enum PersonGender {
	
	
	M("M"), // Male
	F("F"), // Female
	U("U"); // Undefined
	
	private String value;
	
	private PersonGender(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static PersonGender  getEnumByCode(String code) {

		List<PersonGender> stateList = Arrays.asList(PersonGender.values());
		PersonGender statecard = stateList.stream().filter(state -> state.value.equalsIgnoreCase(code)).findAny().orElse(null);
		return statecard;
	}

}
