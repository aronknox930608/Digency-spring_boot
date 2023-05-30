package ma.digency.gov.amc.utils.enumeration;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;

public enum EntityNameEnum {

    BOOKING_SCHOOL,
    PUBLICATION,
    BOOKING_STAND,
    EXHIBITOR;

    public static EntityNameEnum from(String status) {
        for (EntityNameEnum b : EntityNameEnum.values()) {
            if (b.name().equalsIgnoreCase(status)) {
                return b;
            }
        }
        throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_ENTITY_NAME);
    }
}
