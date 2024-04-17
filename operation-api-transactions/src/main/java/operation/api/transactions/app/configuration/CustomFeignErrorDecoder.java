package operation.api.transactions.app.configuration;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import operation.api.transactions.app.exceptions.BadRequestException;
import operation.api.transactions.app.exceptions.ErrorResponse;
import operation.api.transactions.app.exceptions.GlobalApiException;
import operation.api.transactions.app.exceptions.NotFoundException;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		ErrorResponse exceptionresponse = extractBankingCoreGlobalException(response);

		switch (response.status()) {
		case 400:
			return new BadRequestException(exceptionresponse.getMessage());
		case 401:
			return new NotFoundException(exceptionresponse.getMessage());
		case 404:
			return new NotFoundException(exceptionresponse.getMessage());
		case 500:
			return new GlobalApiException(exceptionresponse.getMessage());
		default:
			return new Exception("Common Feign Exception");
		}

	}

	private ErrorResponse extractBankingCoreGlobalException(Response response) {

		ErrorResponse exceptionMessage = null;
		Reader reader = null;
		// capturing error message from response body.
		try {
			reader = response.body().asReader(StandardCharsets.UTF_8);
			String result = IOUtils.toString(reader);
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			exceptionMessage = mapper.readValue(result, ErrorResponse.class);
		} catch (IOException e) {
			log.error("IO Exception on reading exception message feign client" + e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error("IO Exception on reading exception message feign client" + e);
			}
		}
		return exceptionMessage;
	}

}
