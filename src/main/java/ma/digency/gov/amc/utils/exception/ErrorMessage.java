package ma.digency.gov.amc.utils.exception;

import java.io.Serializable;

public interface ErrorMessage extends Serializable {

    /**
     * To get the error message.
     *
     * @return the error message
     */
    String getMessage();

    /**
     * To get the error code.
     *
     * @return the error code.
     */
    String name();

}
