package operation.api.transactions.app.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportResponseDto {
	
	private String fecha;
	private String cliente;
	private String numeroCuenta;
	private String tipo;
	private BigDecimal saldoInicial; // Balance
	private boolean estado;
	private BigDecimal movimiento; // Valor TRX
	private BigDecimal saldoDisponible; // Balance + Valor TRX 

}
