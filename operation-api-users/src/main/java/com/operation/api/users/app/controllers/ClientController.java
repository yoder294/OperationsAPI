package com.operation.api.users.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operation.api.users.app.dtos.ClientBasicInfoResponseDto;
import com.operation.api.users.app.dtos.ClientRequestDto;
import com.operation.api.users.app.dtos.ClientResponseDto;
import com.operation.api.users.app.exceptions.SuccessResponseTemp;
import com.operation.api.users.app.services.IClientMGService;
import com.operation.api.users.app.services.IClientRDService;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

	@Autowired
	private IClientMGService clientMGService;

	@Autowired
	private IClientRDService clientRDService;

	@PostMapping("/create")
	public ResponseEntity<?> createClient(@RequestBody ClientRequestDto requestDto) {

		return ResponseEntity.created(null)
				.body(new SuccessResponseTemp<ClientResponseDto>(clientMGService.save(requestDto)));
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllClients() {
		return ResponseEntity.ok().body(new SuccessResponseTemp<List<ClientResponseDto>>(clientRDService.findAll()));
	}

	@GetMapping("/identificationNumber/{idt}")
	public ResponseEntity<?> findByIdentificationNumber(@PathVariable(required = true) String idt) {
		return ResponseEntity.ok().body(
				new SuccessResponseTemp<ClientBasicInfoResponseDto>(clientRDService.findByIdentificationNumber(idt)));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getClientById(@PathVariable(required = true) Long id) {
		return ResponseEntity.ok().body(new SuccessResponseTemp<ClientResponseDto>(clientRDService.findById(id)));
	}

}
