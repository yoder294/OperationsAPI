package operation.api.transactions.app.services;

import java.math.BigDecimal;

import operation.api.transactions.app.dtos.AccountCreateRequestDto;
import operation.api.transactions.app.dtos.AccountResponseDto;

public interface IAccountMGService {
	
	AccountResponseDto save(AccountCreateRequestDto requestDto);
	void activeStateAccount(String accountNumber);
	void rechargeBalance(BigDecimal balance, String accountNumber);

}
