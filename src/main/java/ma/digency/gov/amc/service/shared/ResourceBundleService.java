package ma.digency.gov.amc.service.shared;

import java.util.Locale;

public interface ResourceBundleService {

    /**
     * To search a message by his code
     *
     * @param code the message code
     * @return the found message
     */
    String getMessage(String code);

    /**
     * To get the user {@link Locale}.
     *
     * @return the user locale
     */
    Locale getLocale();
}
