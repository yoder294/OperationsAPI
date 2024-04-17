package operation.api.transactions.app.services.impls;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import operation.api.transactions.app.dtos.ReportResponseDto;
import operation.api.transactions.app.dtos.TransactionRequestDto;
import operation.api.transactions.app.dtos.TransactionResponseDto;
import operation.api.transactions.app.dtos.restclient.ClientBasicInfoResponseDto;
import operation.api.transactions.app.entitys.Account;
import operation.api.transactions.app.entitys.Transaction;
import operation.api.transactions.app.exceptions.BadRequestException;
import operation.api.transactions.app.exceptions.GlobalApiException;
import operation.api.transactions.app.exceptions.NotFoundException;
import operation.api.transactions.app.exceptions.SuccessResponseTemp;
import operation.api.transactions.app.repositories.ITransactionRepository;
import operation.api.transactions.app.services.IAccountMGService;
import operation.api.transactions.app.services.IAccountRDService;
import operation.api.transactions.app.services.ITransactionMGService;
import operation.api.transactions.app.services.ITransactionRDService;
import operation.api.transactions.app.services.rest.ClientFeignClientRest;
import operation.api.transactions.app.util.TransactionUtil;
import operation.api.transactions.app.util.helpers.TransactionHelper;
import operation.api.transactions.app.util.mappers.TransactionMapper;

@Service
public class TransactionServiceImpl implements ITransactionMGService, ITransactionRDService {

	@Autowired
	private ITransactionRepository transactionRepository;
	@Autowired
	private IAccountRDService accountRDService;
	@Autowired
	private IAccountMGService accountMGService;
	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private ClientFeignClientRest clientFeignClientRest;

	private TransactionHelper transactionHelper;

	@Override
	public TransactionResponseDto save(TransactionRequestDto dto) {
		transactionHelper = new TransactionHelper(dto);
		transactionHelper.validateInputs();

		try {

			Account account = accountRDService.findByAccountNumber(dto.getAccountNumber());

			Transaction transaction = transactionMapper.transactionRequestDtoToTransaction(dto);
			transaction.setAccount(account);
			transaction.setBalance(account.getBalance()); // balanceAnterior

			accountMGService.rechargeBalance(dto.getBalance(), dto.getAccountNumber()); // Validate

			transactionRepository.save(transaction);

			return transactionMapper.transactionToTransactionResponseDto(transaction);
		} catch (BadRequestException e) {
			throw e;
		} catch (GlobalApiException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalApiException("Error persitence Trx.");
		}
	}

	@Override
	public List<ReportResponseDto> filterTransactionByClientAndDate(String client, String dateFilter) {
		try {

			SuccessResponseTemp<ClientBasicInfoResponseDto> clientResponseInfo = clientFeignClientRest
					.findByIdentificationNumber(client);

			List<Transaction> transactions = ((List<Transaction>) transactionRepository.findAll()).stream()
					.filter(t -> t.getAccount().getClientId().equals(client)
							&& TransactionUtil.localDateTimeToDate(t.getDateTrx()).equals(dateFilter))
					.sorted(Comparator.comparing(Transaction::getDateTrx).reversed()).collect(Collectors.toList());

			if (transactions.size() == 0) {
				throw new NotFoundException("No hay registros, para los filtros seleccionados.");
			}

			return transactionMapper.transactionsToReportResponseDtos(transactions).stream().map(p -> {
				p.setCliente(clientResponseInfo.getData().getFullName());
				return p;
			}).collect(Collectors.toList());

		} catch (BadRequestException e) {
			throw e;
		} catch (GlobalApiException e) {
			throw e;
		} catch (Exception e) {
			throw new NotFoundException("No hay registros, para los filtros seleccionados.");
		}
	}

}
