package operation.api.transactions.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import operation.api.transactions.app.dtos.ReportResponseDto;
import operation.api.transactions.app.dtos.TransactionRequestDto;
import operation.api.transactions.app.dtos.TransactionResponseDto;
import operation.api.transactions.app.exceptions.SuccessResponseTemp;
import operation.api.transactions.app.services.ITransactionMGService;
import operation.api.transactions.app.services.ITransactionRDService;

@RestController
@RequestMapping("/api/movimientos")
public class TransactionController {

	@Autowired
	private ITransactionMGService transactionMGService;

	@Autowired
	private ITransactionRDService transactionRDService;

	@PostMapping("/")
	public ResponseEntity<?> balanceTransaction(@RequestBody(required = true) TransactionRequestDto requestDto) {
		return ResponseEntity.created(null)
				.body(new SuccessResponseTemp<TransactionResponseDto>(transactionMGService.save(requestDto)));
	}

	@GetMapping("/reporte")
	public ResponseEntity<?> getTransactionsByClientAndDate(@RequestParam(name = "client") String clientId,
			@RequestParam(name = "fecha") String dateFormatDMY) {
		return ResponseEntity.ok().body(new SuccessResponseTemp<List<ReportResponseDto>>(
				transactionRDService.filterTransactionByClientAndDate(clientId, dateFormatDMY)));
	}

}
