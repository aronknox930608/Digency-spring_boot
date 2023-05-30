package ma.digency.gov.amc.utils.exception;

public abstract class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1050819020129597205L;

    private final ErrorMessage error;
    private final String code;


    /**
     * Construct a {@link BusinessException} with an {@link ErrorMessage}.
     *
     * @param error the exception {@link ErrorMessage}
     */
    public BusinessException(ErrorMessage error) {
        super(error.getMessage());
        this.error = error;
        this.code = error.name();
    }

    /**
     * Construct a {@link BusinessException} with an {@link ErrorMessage} and a message.
     *
     * @param error   the exception {@link ErrorMessage}
     * @param message the exception error message
     */
    public BusinessException(ErrorMessage error, String message) {
        super(message);
        this.error = error;
        this.code = error.name();
    }

    /**
     * Construct a {@link BusinessException} with an {@link ErrorMessage} and a {@link Throwable}.
     *
     * @param error the exception {@link ErrorMessage}
     * @param cause the exception cause
     */
    public BusinessException(ErrorMessage error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.code = error.name();
    }

    /**
     * Error getter.
     *
     * @return the exception {@link ErrorMessage}
     */
    public ErrorMessage getError() {
        return error;
    }

    /**
     * Error code getter.
     *
     * @return
     */
    public String getCode() {
        return code;
    }

}
