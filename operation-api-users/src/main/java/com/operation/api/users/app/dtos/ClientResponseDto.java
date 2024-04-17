package com.operation.api.users.app.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponseDto {

	private Long id;
	private String fullName;
	private String gender;
	private Integer age;
	private String identificationNumber;
	private String address;
	private Integer telephone;
	private boolean state;
	private Integer password;
}

