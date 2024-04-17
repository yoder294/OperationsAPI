package operation.api.transactions.app.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountRechargeBalanceRequestDto {

	private BigDecimal balance; 
	private String accountNumber;
}
