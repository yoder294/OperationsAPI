package com.operation.api.users.app.util.mappers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.operation.api.users.app.dtos.ClientBasicInfoResponseDto;
import com.operation.api.users.app.dtos.ClientRequestDto;
import com.operation.api.users.app.dtos.ClientResponseDto;
import com.operation.api.users.app.entitys.Client;
import com.operation.api.users.app.util.enums.PersonGender;

@Mapper(componentModel = "spring")
public interface ClientMapper {

	@Mappings({ @Mapping(target = "name", source = "fullName", qualifiedByName = "capitalize"),
			@Mapping(target = "age", source = "age"),
			@Mapping(target = "identificationNumber", source = "identificationNumber"),
			@Mapping(target = "address", source = "address"), @Mapping(target = "telephone", source = "telephone"),
			@Mapping(target = "password", source = "password"),
			@Mapping(target = "gender", source = "gender", qualifiedByName = "stringToGender"),
			@Mapping(target = "id", ignore = true), @Mapping(target = "state", constant = "true"), })
	Client clientRequestDtoToClient(ClientRequestDto clientRequestDto);

	@Mappings({ @Mapping(target = "fullName", source = "name"), @Mapping(target = "age", source = "age"),
			@Mapping(target = "identificationNumber", source = "identificationNumber"),
			@Mapping(target = "gender", source = "gender", qualifiedByName = "genderTostring"), 
			@Mapping(target = "state", source = "state")})
	ClientBasicInfoResponseDto clientToClientBasicInfoResponseDto(Client client);

	@Mappings({ @Mapping(target = "fullName", source = "name"), @Mapping(target = "age", source = "age"),
			@Mapping(target = "identificationNumber", source = "identificationNumber"),
			@Mapping(target = "address", source = "address"), @Mapping(target = "telephone", source = "telephone"),
			@Mapping(target = "password", source = "password"),
			@Mapping(target = "gender", source = "gender", qualifiedByName = "genderTostring"),
			@Mapping(target = "id", source = "id"), @Mapping(target = "state", source = "state"), })
	ClientResponseDto clientToClientResponseDto(Client client);

	List<ClientResponseDto> clientsToClientResponseDtos(List<Client> clients);

	@Named("stringToGender")
	public static PersonGender stringToGender(String gender) {

		PersonGender genderResult = PersonGender.U;

		if (gender != null) {
			genderResult = PersonGender.getEnumByCode(gender.trim().toUpperCase());
		}

		return genderResult;
	}

	@Named("genderTostring")
	public static String genderTostring(PersonGender gender) {
		return gender.getValue();
	}

	@Named("capitalize")
	public static String capitalizeFullName(String name) {

		return Stream.of(name.trim().split(" "))
				.map(str -> str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase())
				.collect(Collectors.joining(" "));
	}

}
