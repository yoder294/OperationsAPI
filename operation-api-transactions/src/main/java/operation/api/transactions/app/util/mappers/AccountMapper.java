package operation.api.transactions.app.util.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import operation.api.transactions.app.dtos.AccountCreateRequestDto;
import operation.api.transactions.app.dtos.AccountResponseDto;
import operation.api.transactions.app.entitys.Account;
import operation.api.transactions.app.util.TransactionUtil;
import operation.api.transactions.app.util.enums.AccountType;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	@Mappings({ @Mapping(target = "id", ignore = true),
			@Mapping(target = "accountNumber", expression = "java(this.generateAccountNumber())"),
			@Mapping(target = "state", ignore = true, defaultValue = "false"),
			@Mapping(target = "transactions", ignore = true),
			@Mapping(target = "accountType", source = "accountType", qualifiedByName = "stringToAccountType"),
			@Mapping(target = "balance", source = "balance", qualifiedByName = "balanceByDefault"),
			@Mapping(target = "clientId", source = "client"), })
	Account accountCreateRequestDtoToAccount(AccountCreateRequestDto requestDto);

	@Mappings({ @Mapping(target = "accountNumber", source = "accountNumber"),
			@Mapping(target = "state", source = "state"),
			@Mapping(target = "accountType", source = "accountType", qualifiedByName = "accountTypeToString"),
			@Mapping(target = "balance", source = "balance"), 
			@Mapping(target = "client", ignore = true) })
	AccountResponseDto accountToAccountResponseDto(Account account);

	@Named("stringToAccountType")
	public static AccountType stringToAccountType(String type) {
		return AccountType.getEnumByType(type.trim().toUpperCase());
	}

	@Named("accountTypeToString")
	public static String accountTypeToString(AccountType accountType) {
		return accountType.getValue() == "A" ? "Ahorros" : "Corriente";
	}

	@Named("balanceByDefault")
	public static BigDecimal balanceByDefault(BigDecimal balance) {
		return balance == null ? BigDecimal.ZERO : balance;
	}

	default String generateAccountNumber() {
		return TransactionUtil.generate6RandomNumbers();
	}

}
