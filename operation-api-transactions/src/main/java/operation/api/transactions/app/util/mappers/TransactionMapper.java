package operation.api.transactions.app.util.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import operation.api.transactions.app.dtos.ReportResponseDto;
import operation.api.transactions.app.dtos.TransactionRequestDto;
import operation.api.transactions.app.dtos.TransactionResponseDto;
import operation.api.transactions.app.entitys.Account;
import operation.api.transactions.app.entitys.Transaction;
import operation.api.transactions.app.util.TransactionUtil;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = { AccountMapper.class })
public interface TransactionMapper {

	@Mappings({ @Mapping(target = "dateTrx", expression = "java(this.initDate())"),
			@Mapping(target = "status", constant = "true"), 
			@Mapping(target = "description", expression = "java(this.upperDescription(requestDto.getDescription()))"),
			@Mapping(target = "valueTrx", source = "balance"), @Mapping(target = "id", ignore = true),
			@Mapping(target = "balance", ignore = true), @Mapping(target = "account", ignore = true), })
	Transaction transactionRequestDtoToTransaction(TransactionRequestDto requestDto);

	@Mappings({ @Mapping(target = "accountNumber", source = "account", qualifiedByName = "getAccountNumber"),
			@Mapping(target = "accountType", source = "account", qualifiedByName = "getAccountType"),
			@Mapping(target = "status", source = "status"), 
			@Mapping(target = "balance", expression = "java(this.getBalanceFinal(transaction.getValueTrx(),transaction.getBalance()))"),
			@Mapping(target = "description", source = "description"), })
	TransactionResponseDto transactionToTransactionResponseDto(Transaction transaction);

	@Named("getAccountNumber")
	public static String getAccountNumber(Account account) {
		return account.getAccountNumber();
	}

	@Named("getAccountType")
	public static String getAccountType(Account account) {
		return AccountMapper.accountTypeToString(account.getAccountType());
	}

	default LocalDateTime initDate() {
		return LocalDateTime.now();
	}
	
	default String upperDescription(String description) {
		return description.trim().toUpperCase();
	}

	// -------- REPORT --------
	@Mappings({ @Mapping(target = "fecha", source = "dateTrx", qualifiedByName = "localDateTimeToDate"),
			@Mapping(target = "cliente", source = "account", qualifiedByName = "getClientAccountByTrx"),
			@Mapping(target = "numeroCuenta", source = "account", qualifiedByName = "getAccountNumber"),
			@Mapping(target = "tipo", source = "account", qualifiedByName = "getAccountType"),
			@Mapping(target = "saldoInicial", source = "balance"), @Mapping(target = "estado", source = "status"),
			@Mapping(target = "movimiento", source = "valueTrx"),
			@Mapping(target = "saldoDisponible", expression = "java(this.getBalanceFinal(transaction.getValueTrx(),transaction.getBalance()))"), })
	ReportResponseDto transactionToReportResponseDto(Transaction transaction);

	List<ReportResponseDto> transactionsToReportResponseDtos(List<Transaction> transactions);

	@Named("localDateTimeToDate")
	public static String localDateTimeToDate(LocalDateTime localDateTime) {
		return  TransactionUtil.localDateTimeToDate(localDateTime);
	}

	@Named("getClientAccountByTrx")
	public static String getClientAccountByTrx(Account account) {
		return account.getClientId();
	}

	default BigDecimal getBalanceFinal(BigDecimal value, BigDecimal balance) {
		return balance.add(value);
	}
}
