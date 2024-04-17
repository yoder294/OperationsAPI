package operation.api.transactions.app.dtos;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCreateRequestDto {

	private String accountType;
	private String client;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal balance;	
	
}
