package operation.api.transactions.app.services;

import operation.api.transactions.app.dtos.TransactionRequestDto;
import operation.api.transactions.app.dtos.TransactionResponseDto;

public interface ITransactionMGService {
	
	TransactionResponseDto save(TransactionRequestDto dto);

}
