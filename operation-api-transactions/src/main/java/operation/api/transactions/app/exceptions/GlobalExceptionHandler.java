package operation.api.transactions.app.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {

		return ResponseEntity
				.badRequest()
				.body(ErrorResponse.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMsg())
				.success(false)
				.build());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {

		return ((BodyBuilder) ResponseEntity
				.notFound())
				.body(ErrorResponse.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.message(ex.getMsg())
				.success(false)
				.build());
	}
	
	@ExceptionHandler(GlobalApiException.class)
	public ResponseEntity<?> handleNotFoundException(GlobalApiException ex) {

		return ResponseEntity
				.internalServerError()
				.body(ErrorResponse.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(ex.getMsg())
				.success(false)
				.build());
	}
	
	  @ExceptionHandler({Exception.class})
	    protected ResponseEntity<?> handleException(Exception e, Locale locale) {
	        return ResponseEntity
	                .internalServerError()
	                .body(ErrorResponse.builder()
	        				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
	        				.message("Exception occur inside API " + e)
	        				.success(false)
	        				.build());
	  }
	
}

