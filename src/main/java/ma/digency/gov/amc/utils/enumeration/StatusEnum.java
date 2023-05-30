package ma.digency.gov.amc.utils.enumeration;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;

public enum StatusEnum {
    PENDING,
    REJECTED,
    DELETED,
    OPEN,
    CLOSE,
    ACCEPTED,
    VALID_SUBSCRIPTION;

    public static StatusEnum from(String status) {
        for (StatusEnum b : StatusEnum.values()) {
            if (b.name().equalsIgnoreCase(status)) {
                return b;
            }
        }
        throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_STATUS);
    }

    public static StatusEnum determineStatus(String status) {
        for (StatusEnum b : StatusEnum.values()) {
            if (b.name().equalsIgnoreCase(status)) {
                return b;
            }
        }
        return null;
    }

}
