package com.operation.api.users.app.services;

import java.util.List;

import com.operation.api.users.app.dtos.ClientBasicInfoResponseDto;
import com.operation.api.users.app.dtos.ClientResponseDto;

public interface IClientRDService {
	
	List<ClientResponseDto> findAll();
	ClientResponseDto findById(Long id);
	ClientBasicInfoResponseDto findByIdentificationNumber(String identificationNumber);

}
