package operation.api.transactions.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import operation.api.transactions.app.dtos.AccountCreateRequestDto;
import operation.api.transactions.app.dtos.AccountResponseDto;
import operation.api.transactions.app.exceptions.SuccessResponseTemp;
import operation.api.transactions.app.services.IAccountMGService;

@RefreshScope // onlly properti @value
@RestController
@RequestMapping("/api/cuentas")
public class AccountController {

	@Autowired
	private IAccountMGService accountMGService;
	
	/*@Autowired
	private IAccountRDService accountRDService;
	*/
	
	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequestDto requestDto) {
		
		return ResponseEntity
				.created(null)
				.body(new SuccessResponseTemp<AccountResponseDto>(accountMGService.save(requestDto)));
	}
	
	
	@PutMapping("/active/{numberAccount}")
	public ResponseEntity<?> activeAccount(@PathVariable(required = true) String numberAccount) {
		
		accountMGService.activeStateAccount(numberAccount);
		return ResponseEntity
				.ok()
				.body(new SuccessResponseTemp<AccountResponseDto>(null,"account active successfully."));
	}
	
	
	/*@PutMapping("/recharge")
	public ResponseEntity<?> rechargeBalance(@RequestBody(required = true) AccountRechargeBalanceRequestDto requestDto) {
		
		persistenceService.rechargeBalance(requestDto.getBalance(), requestDto.getAccountNumber());
		return ResponseEntity
				.ok()
				.body(new SuccessResponseTemp<AccountResponseDto>(null,"account recharge balance successfully."));
	}*/
	
}
