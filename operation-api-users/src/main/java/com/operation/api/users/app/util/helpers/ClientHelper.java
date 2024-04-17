package com.operation.api.users.app.util.helpers;

import com.operation.api.users.app.dtos.ClientRequestDto;
import com.operation.api.users.app.exceptions.BadRequestException;
import com.operation.api.users.app.util.ValidationGeneric;
import com.operation.api.users.app.util.enums.PersonGender;

import lombok.Getter;

public class ClientHelper {
	
	@Getter
	private ClientRequestDto requestDto;

	public ClientHelper(ClientRequestDto requestDto) {
		this.requestDto = requestDto;
		isClientRequestNull();
	}

	private void isClientRequestNull() {
		if (requestDto == null) {
			throw new BadRequestException();
		}
	}

	public void validationInputs() {

		if (!ValidationGeneric.isValidString(requestDto.getFullName())) {
			throw new BadRequestException("El campo fullName es requerido.");
		}

		if (ValidationGeneric.isValidString(requestDto.getGender())) {
			if (PersonGender.getEnumByCode(requestDto.getGender().trim().toUpperCase()) == null) {
				throw new BadRequestException("El campo gender accepta los siguientes valores {M,F,m,f}.");
			}
		}

		if (!ValidationGeneric.isValidString(requestDto.getAddress())) {
			throw new BadRequestException("El campo address es requerido.");
		}

		if (!(ValidationGeneric.isValidNumber(requestDto.getAge()) && requestDto.getAge() >= 18)) {
			throw new BadRequestException("El campo age debe ser mayor o igual a 18.");
		}

		if (!ValidationGeneric.stringValidLengtOnlyNumber(requestDto.getIdentificationNumber(), 10)) {
			throw new BadRequestException("El campo identificationNumber solo acepta numeros y de 10 digitos.");
		}

		if (!ValidationGeneric.isValidNumberLength(requestDto.getTelephone(), 8)) {
			throw new BadRequestException("El campo telephone debe ser de 8 digitos.");
		}

		if (!ValidationGeneric.isValidNumberLength(requestDto.getPassword(), 5)) {
			throw new BadRequestException("El campo password debe ser de 5 digitos.");
		}
	}

}
