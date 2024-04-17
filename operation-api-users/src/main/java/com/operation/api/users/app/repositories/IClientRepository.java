package com.operation.api.users.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.operation.api.users.app.entitys.Client;


public interface IClientRepository extends CrudRepository<Client, Long> {
	
	Optional<Client> findByIdentificationNumber(String identificationNumber);

}
