package com.operation.api.users.app.exceptions;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponseTemp<T> {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;
	private boolean success;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer length = null;
	private String message = null;

	@SuppressWarnings("rawtypes")
	public SuccessResponseTemp(T data, int length, String message) {
		this.success = true;
		this.data = data;
		this.length = length;
		this.message = message;
		if (length == 0) {
			if (this.data instanceof List) {
				this.length = ((List) this.data).size();
			}

			if (this.data instanceof Map) {
				this.length = ((Map) this.data).size();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public SuccessResponseTemp(T data, String message) {
		this.success = true;
		this.data = data;
		this.message = message;

		if (this.data instanceof List) {
			this.length = ((List) this.data).size();
		}

		if (this.data instanceof Map) {
			this.length = ((Map) this.data).size();
		}
	}

	public SuccessResponseTemp(T data, Integer length) {
		this.success = true;
		this.data = data;
		this.length = length;
	}

	public SuccessResponseTemp(T data) {
		this.success = true;
		this.data = data;
	}

}
