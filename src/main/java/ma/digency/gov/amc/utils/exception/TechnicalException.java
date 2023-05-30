package ma.digency.gov.amc.utils.exception;

public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = 973261455742086320L;


    private final ErrorMessage error;
    private final String code;

    /**
     * Public constructor.
     * <p>
     * To construct a {@link TechnicalException} with :
     * </p>
     *
     * @param error the exception {@link ErrorMessage}
     * @param cause the exception cause
     */
    public TechnicalException(ErrorMessage error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.code = error.name();
    }


    /**
     * Public constructor.
     * <p>
     * To construct a {@link TechnicalException} with :
     * </p>
     *
     * @param error the exception {@link ErrorMessage}
     */
    public TechnicalException(ErrorMessage error) {
        super(error.getMessage());
        this.error = error;
        this.code = error.name();
    }


    /**
     * Public getter of the field error.
     *
     * @return the error
     */
    public ErrorMessage getError() {
        return error;
    }


    /**
     * Public getter of the field code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }


}
