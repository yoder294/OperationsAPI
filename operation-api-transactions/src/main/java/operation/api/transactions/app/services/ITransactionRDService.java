package operation.api.transactions.app.services;

import java.util.List;

import operation.api.transactions.app.dtos.ReportResponseDto;

public interface ITransactionRDService {

	List<ReportResponseDto> filterTransactionByClientAndDate(String client, String dateFilter);
	
}
