package operation.api.transactions.app.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionRequestDto {

	private String accountNumber;
	private BigDecimal balance;
	private String description;
	
}
