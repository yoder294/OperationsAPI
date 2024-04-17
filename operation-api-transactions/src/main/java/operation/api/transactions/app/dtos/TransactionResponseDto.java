package operation.api.transactions.app.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionResponseDto {

	private String accountNumber;
	private String accountType;
	private BigDecimal balance;
	private String description;
	private boolean status;
	
}
