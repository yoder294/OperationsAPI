package operation.api.transactions.app.util.helpers;

import lombok.Getter;
import operation.api.transactions.app.dtos.AccountCreateRequestDto;
import operation.api.transactions.app.exceptions.BadRequestException;
import operation.api.transactions.app.util.TransactionUtil;
import operation.api.transactions.app.util.enums.AccountType;

public class AccountHelper {

	@Getter
	private AccountCreateRequestDto requestDto;

	public AccountHelper(AccountCreateRequestDto requestDto) {
		this.requestDto = requestDto;
		isAccountCreateRequestNull();
	}

	private void isAccountCreateRequestNull() {
		if (requestDto == null) {
			throw new BadRequestException();
		}
	}

	public void validateInputs() {

		if (!TransactionUtil.isValidString(requestDto.getClient())) {
			throw new BadRequestException("El campo client es requerido.");
		}

		if (!TransactionUtil.isValidString(requestDto.getAccountType())) {
			throw new BadRequestException("El campo accountType es requerido.");
		} else {
			if (AccountType.getEnumByType(requestDto.getAccountType().trim().toUpperCase()) == null) {
				throw new BadRequestException("El campo accountType solo acepta los sigueintes valores [A,a,C,c].");
			}
		}

		if (requestDto.getBalance() != null) {
			if (!TransactionUtil.compareNumberGreaterOrEqualZero(requestDto.getBalance())) {
				throw new BadRequestException("El campo balance debe ser >= 0.");
			}
		}

	}

}
