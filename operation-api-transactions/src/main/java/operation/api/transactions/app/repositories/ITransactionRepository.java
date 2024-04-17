package operation.api.transactions.app.repositories;

import org.springframework.data.repository.CrudRepository;

import operation.api.transactions.app.entitys.Transaction;

public interface ITransactionRepository extends CrudRepository<Transaction, Long> {

}
