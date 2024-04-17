package com.operation.api.users.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientBasicInfoResponseDto {
	
	private String fullName;
	private Integer identificationNumber;
	private String gender;
	private Integer age;
	private boolean state;

}
