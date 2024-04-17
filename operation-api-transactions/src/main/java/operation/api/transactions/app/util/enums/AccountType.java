package operation.api.transactions.app.util.enums;

import java.util.Arrays;
import java.util.List;

public enum AccountType {

	A("A"), // AHORROS
	C("C"); // CORRIENTE
	
	private String value;
	
	private AccountType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	

	public static AccountType getEnumByType(String code) {

		List<AccountType> stateList = Arrays.asList(AccountType.values());
		AccountType statecard = stateList.stream().filter(state -> state.value.equalsIgnoreCase(code)).findAny().orElse(null);
		return statecard;
	}
	
}
