package com.operation.api.users.app.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientRequestDto {

	private String fullName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String gender;

	private Integer age;

	private String identificationNumber;

	private String address;

	private Integer telephone;

	private Integer password;

}
