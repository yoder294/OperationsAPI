package operation.api.transactions.app.services.impls;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import operation.api.transactions.app.dtos.AccountCreateRequestDto;
import operation.api.transactions.app.dtos.AccountResponseDto;
import operation.api.transactions.app.dtos.restclient.ClientBasicInfoResponseDto;
import operation.api.transactions.app.entitys.Account;
import operation.api.transactions.app.exceptions.GlobalApiException;
import operation.api.transactions.app.exceptions.NotFoundException;
import operation.api.transactions.app.exceptions.SuccessResponseTemp;
import operation.api.transactions.app.repositories.IAccountRepository;
import operation.api.transactions.app.services.IAccountMGService;
import operation.api.transactions.app.services.IAccountRDService;
import operation.api.transactions.app.services.rest.ClientFeignClientRest;
import operation.api.transactions.app.util.TransactionUtil;
import operation.api.transactions.app.util.helpers.AccountHelper;
import operation.api.transactions.app.util.mappers.AccountMapper;

@Service
public class AccountServiceImpl implements IAccountMGService, IAccountRDService {

	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private ClientFeignClientRest clientFeignClientRest;

	private AccountHelper accountHelper;

	@Transactional
	@Override
	public AccountResponseDto save(AccountCreateRequestDto requestDto) {

		accountHelper = new AccountHelper(requestDto);
		accountHelper.validateInputs();

		try {

			SuccessResponseTemp<ClientBasicInfoResponseDto> client = clientFeignClientRest
					.findByIdentificationNumber(requestDto.getClient());

			Account account = accountMapper.accountCreateRequestDtoToAccount(requestDto);
			accountRepository.save(account);
			
			AccountResponseDto response = accountMapper.accountToAccountResponseDto(account);
		    response.setClient(client.getData().getFullName());
		    
			return response;
		}
		catch (NotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		catch (GlobalApiException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new GlobalApiException("Error persitence account");
		}

	}

	@Transactional
	@Override
	public void activeStateAccount(String accountNumber) {

		try {

			findByAccountNumber(accountNumber);
			accountRepository.activeStateAccount(accountNumber);

		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalApiException("Error updating account.");
		}

	}

	@Transactional
	@Override
	public void rechargeBalance(BigDecimal balance, String accountNumber) {

		try {

			Account account = accountIsActive(accountNumber);
			BigDecimal balanceFinal = account.getBalance().add(balance);

			if (!TransactionUtil.compareNumberGreaterOrEqualZero(balanceFinal)) {
				throw new GlobalApiException("Saldo no disponible");
			}

			accountRepository.rechargeBalance(balanceFinal, accountNumber);

		} catch (NotFoundException e) {
			throw e;
		} catch (GlobalApiException e) {
			throw e;
		} catch (Exception e) {
			throw new GlobalApiException("Error updating account.");
		}
	}

	@Override
	public Account accountIsActive(String accountNumber) {

		Account account = findByAccountNumber(accountNumber);
		if (account != null && !account.isState()) {
			throw new GlobalApiException(
					"Debe de ACTIVAR la cuenta " + accountNumber + " para poder realizar movimientos.");
		}
		return account;
	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new NotFoundException("No existe una cuenta con número: " + accountNumber));
	}

}
