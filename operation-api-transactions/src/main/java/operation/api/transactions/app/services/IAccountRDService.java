package operation.api.transactions.app.services;

import operation.api.transactions.app.entitys.Account;

public interface IAccountRDService {
	
	Account findByAccountNumber(String accountNumber);
	Account accountIsActive(String accountNumber);

}
