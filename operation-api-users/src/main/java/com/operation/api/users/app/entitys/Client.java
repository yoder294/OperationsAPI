package com.operation.api.users.app.entitys;

import java.io.Serializable;

import com.operation.api.users.app.util.enums.PersonGender;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "client_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Integer password;

	@NotNull
	private boolean state;

	public Client(String name, PersonGender gender, Integer age, String idt, String address, Integer telephone,
			Integer password) {
		super(name, gender, age, idt, address, telephone);
		this.password = password;
		this.state = false;
	}

}
