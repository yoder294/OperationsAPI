package operation.api.transactions.app.dtos.restclient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBasicInfoResponseDto {
	
	private String fullName;
	private Integer identificationNumber;
	private String gender;
	private Integer age;
	private boolean state;

}

