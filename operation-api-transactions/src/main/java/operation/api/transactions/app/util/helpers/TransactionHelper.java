package operation.api.transactions.app.util.helpers;

import lombok.Getter;
import operation.api.transactions.app.dtos.TransactionRequestDto;
import operation.api.transactions.app.exceptions.BadRequestException;
import operation.api.transactions.app.util.TransactionUtil;

public class TransactionHelper {

	@Getter
	private TransactionRequestDto requestDto;

	public TransactionHelper(TransactionRequestDto requestDto) {
		this.requestDto = requestDto;
		istransactionRequesNull();
	}

	private void istransactionRequesNull() {
		if (requestDto == null) {
			throw new BadRequestException("Body is required");
		}
	}

	public void validateInputs() {

		if (!TransactionUtil.isValidString(requestDto.getAccountNumber())) {
			throw new BadRequestException("el campo accountNumber es requerido.");
		}

		if (!TransactionUtil.isValidString(requestDto.getDescription())) {
			throw new BadRequestException("el campo description es requerido.");
		}

		if (requestDto.getBalance() == null) {
			throw new BadRequestException("el campo balance es requerido.");
		}

	}

}
