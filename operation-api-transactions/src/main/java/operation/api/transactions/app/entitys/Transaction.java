package operation.api.transactions.app.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "date_trx")
	private LocalDateTime dateTrx;

	@NotEmpty
	@Column(name = "description", length = 20)
	private String description;

	@NotNull
	private BigDecimal balance;

	@NotNull
	@Column(name = "value_trx")
	private BigDecimal valueTrx;

	@NotNull
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

}