package operation.api.transactions.app.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountResponseDto {
  
	private String accountNumber;
	private String accountType;
	private BigDecimal balance;
	private boolean state;
	private String client;
}
