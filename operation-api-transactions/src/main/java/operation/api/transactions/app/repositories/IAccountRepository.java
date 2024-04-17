package operation.api.transactions.app.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import operation.api.transactions.app.entitys.Account;

public interface IAccountRepository extends CrudRepository<Account, Long> {
	
	public Optional<Account> findByAccountNumber(String accountNumber);

	@Modifying
	@Query("UPDATE Account SET state=true WHERE accountNumber=:accountNumber")
	void activeStateAccount(String accountNumber);
	
	@Modifying
	@Query("UPDATE Account SET balance=:balance WHERE accountNumber=:accountNumber")
	void rechargeBalance(BigDecimal balance, String accountNumber);

}
