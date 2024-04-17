package operation.api.transactions.app.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import operation.api.transactions.app.util.enums.AccountType;

@Entity
@Table(name = "accounts",
       uniqueConstraints = {
		  @UniqueConstraint(columnNames = { "account_number" }, name = "UQ_AccountNumber") }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "account_number", length = 6)
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "account_type", length = 1)
	private AccountType accountType;

	@NotNull
	private BigDecimal balance;

	@NotNull
	private boolean state;
	
	@NotEmpty
	@Column(name = "client_id", nullable = false, length = 10)
	private String clientId;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions;

}
