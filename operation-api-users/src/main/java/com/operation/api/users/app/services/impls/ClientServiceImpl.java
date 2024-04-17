package com.operation.api.users.app.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.operation.api.users.app.dtos.ClientBasicInfoResponseDto;
import com.operation.api.users.app.dtos.ClientRequestDto;
import com.operation.api.users.app.dtos.ClientResponseDto;
import com.operation.api.users.app.entitys.Client;
import com.operation.api.users.app.exceptions.GlobalApiException;
import com.operation.api.users.app.exceptions.NotFoundException;
import com.operation.api.users.app.repositories.IClientRepository;
import com.operation.api.users.app.services.IClientMGService;
import com.operation.api.users.app.services.IClientRDService;
import com.operation.api.users.app.util.helpers.ClientHelper;
import com.operation.api.users.app.util.mappers.ClientMapper;

import jakarta.transaction.Transactional;

@Service
public class ClientServiceImpl implements IClientMGService, IClientRDService {

	@Autowired
	private IClientRepository clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	private ClientHelper clientHelper;

	@Transactional
	@Override
	public ClientResponseDto save(ClientRequestDto requestDto) {

		clientHelper = new ClientHelper(requestDto);
		clientHelper.validationInputs();

		try {
			Client client = clientMapper.clientRequestDtoToClient(requestDto);
			clientRepository.save(client);
			return clientMapper.clientToClientResponseDto(client);
		} catch (DataIntegrityViolationException e) {
			throw new GlobalApiException(
					"Ya existe un cliente con ese número de identificacion: " + requestDto.getIdentificationNumber());
		} catch (Exception e) {
			throw new GlobalApiException("Error persisten entity.");
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Client not exits with id: " + id));

		try {
			clientRepository.delete(client);
		} catch (Exception e) {
			throw new GlobalApiException("Interna error logic");
		}

	}

	@Override
	public List<ClientResponseDto> findAll() {
		try {

			List<Client> clients = (List<Client>) clientRepository.findAll();

			if (clients.size() == 0) {
				return new ArrayList<>();
			}

			return clientMapper.clientsToClientResponseDtos(clients);
		} catch (Exception e) {
			throw new GlobalApiException("Error internal server");
		}
	}

	@Override
	public ClientResponseDto findById(Long id) {
		return clientMapper.clientToClientResponseDto(clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe un cliente con id: " + id)));
	}

	@Override
	public ClientBasicInfoResponseDto findByIdentificationNumber(String identificationNumber) {
		Client client = clientRepository.findByIdentificationNumber(identificationNumber).orElseThrow(
				() -> new NotFoundException("No existe un cliente con esa identificación: " + identificationNumber));

		return clientMapper.clientToClientBasicInfoResponseDto(client);
	}

}
