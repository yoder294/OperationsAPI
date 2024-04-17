package com.operation.api.users.app.entitys;

import java.io.Serializable;

import com.operation.api.users.app.util.enums.PersonGender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons",
uniqueConstraints = {
	  @UniqueConstraint(columnNames = { "identification_number" }, name = "UQ_IDT") }
)
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name", length = 50)
	@NotEmpty
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	private PersonGender gender;

	@NotNull
	private Integer age;

	@NotNull
	@Column(name = "identification_number", length = 10)
	private String identificationNumber;

	@NotEmpty
	@Column(name = "address", length = 100)
	private String address;

	@NotNull
	private Integer telephone;

	public Person(@NotEmpty String name, PersonGender gender, @NotNull Integer age, @NotNull String identificationNumber,
			@NotEmpty String address, @NotNull Integer telephone) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.identificationNumber = identificationNumber;
		this.address = address;
		this.telephone = telephone;
	}

}