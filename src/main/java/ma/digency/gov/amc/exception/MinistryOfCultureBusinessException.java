package ma.digency.gov.amc.exception;

import ma.digency.gov.amc.utils.exception.BusinessException;

public class MinistryOfCultureBusinessException extends BusinessException {

    private static final long serialVersionUID = 1L;

    private final String[] messageParams;

    public MinistryOfCultureBusinessException(MinistryOfCultureMessage error) {
        super(error);
        messageParams = null;
    }

    public MinistryOfCultureBusinessException(MinistryOfCultureMessage error,
                                              String... messageParams) {
        super(error);
        this.messageParams = messageParams;
    }


    public String[] getMessageParams() {
        return messageParams;
    }

}

