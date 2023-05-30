package ma.digency.gov.amc.service.shared;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class DocumentStringMapper {

	private static final String YES = "Oui";

	private static final String NO = "Non";

	private final DateTimeFormatter dateTimeFormatter;

	public DocumentStringMapper() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	public String stringify(Object value) {
		if (value == null) {
			return "";
		}
		if (value.getClass().equals(LocalDate.class)) {
			return ((LocalDate) value).format(dateTimeFormatter);
		}
		if (value.getClass().equals(Boolean.TYPE) || value.getClass().equals(Boolean.class)) {
			return (boolean) value ? YES : NO;
		}
		if (value instanceof Number) {
			if (value instanceof BigDecimal) {
				DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
				decimalFormatSymbols.setGroupingSeparator('.');
				DecimalFormat decimalFormat = new DecimalFormat("#,###", decimalFormatSymbols);
				return decimalFormat.format(value);
			}
			return new DecimalFormat("##.##").format(value);
		}
		return value.toString();
	}
}
