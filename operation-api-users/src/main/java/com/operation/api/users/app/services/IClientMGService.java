package com.operation.api.users.app.services;

import com.operation.api.users.app.dtos.ClientRequestDto;
import com.operation.api.users.app.dtos.ClientResponseDto;

public interface IClientMGService {

	ClientResponseDto save(ClientRequestDto requestDto);
	void delete(Long id);
	
}
