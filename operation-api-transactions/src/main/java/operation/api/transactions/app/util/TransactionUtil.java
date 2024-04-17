package operation.api.transactions.app.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import io.micrometer.common.util.StringUtils;

public class TransactionUtil {
	
	public static boolean isValidString(String value) {
		return (value != null) && !StringUtils.isBlank(value.trim());
	}
	
	public static String generate6RandomNumbers() {
		Long random = (new Random().nextLong(900000L) + 100000L);
		return Long.toString(random);
	}
	
	public static boolean compareNumberGreaterOrEqualZero(BigDecimal value) {
		return (value.signum() >= 0);
	}
	
	public static String localDateTimeToDate(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
	}
	
}
