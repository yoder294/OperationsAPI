package operation.api.transactions.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponseTemp<T> {

	private T data;
	private boolean success;
	private String message = null;

	public SuccessResponseTemp(T data, String message) {
		this.success = true;
		this.data = data;
		this.message = message;
	}

	public SuccessResponseTemp(T data) {
		this.success = true;
		this.data = data;
	}

}
