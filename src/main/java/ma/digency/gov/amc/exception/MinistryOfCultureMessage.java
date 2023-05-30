package ma.digency.gov.amc.exception;

import ma.digency.gov.amc.utils.exception.ErrorMessage;
import org.springframework.http.HttpStatus;

public enum MinistryOfCultureMessage implements ErrorMessage {

    AMC_ACCOUNT_ALREADY_EXISTS,
    AMC_ACCOUNT_NOT_FOUND,
    AMC_BOOKING_SCHOOL_NOT_FOUND,
    AMC_PUBLICATION_NOT_FOUND,
    AMC_EXIHIBITOR_NOT_FOUND,
    AMC_INVALID_STATUS,
    AMC_INTERNAL_SERVER,
    AMC_PERIOD_PLANNING_ERROR,
    AMC_ROLE_NOT_FOUND,
    AMC_INVALID_CODE_OTP,
    AMC_BOOK_PERSON_FOUND,


    AMC_INVALID_PARAMETER, //
    AMC_UNKNOWN_FIELD,
    AMC_UNEXPECTED_ERROR,
    AMC_PROPOSAL_PROJECT_NOT_FOUND,//
    AMC_TECHNICAL_ERROR, //
    AMC_INVALID_ENTITY_NAME, //
    AMC_PLANNING_NOT_FOUND, //
    AMC_COMMISSION_NOT_FOUND, //
    AMC_COMMISSION_ACCOUNT_NOT_FOUND, //
    AMC_TIME_FORMAT_INVALID,
    AMC_ACTIVITY_NOT_FOUND,
    AMC_PUBLISHER_REPRESENTED_NOT_FOUND,
    AMC_BOOKING_STAND_NOT_FOUND,
    AMC_DUPLICATE_MEMBER, AMC_BOOKING_STAND_EXIST, AMC_INVALID_INSCRIPTION, AMC_DOCUMENT_NOT_FOUND, AMC_EDITION_FOUND, AMC_INVALID_DATE_FOUND, AMC_IMPORT_DATA_ERROR, AMC_OPEN_EDITION_ALREADY_EXIST

    ,AMC_EMAIL_IN_USE,
    AMC_OLD_PASSWORD_INVALID,
    AMC_PASSWORD_NOT_CONFIRMED,
    AMC_ROLE_NOT_EDITABLE,
    AMC_ROLE_NOT_REMOVABLE

    ,AMC_ARTIST_ACCOUNT_NOT_FOUND,
    AMC_OPEN_EDITION_MUST_BE_CLOSE,
    AMC_ARTIST_ACCOUNT_REFERENCE_ALREADY_EXIST,
    AMC_BAD_CREDENTIALS,
    AMC_UNAUTHENTIFIED,
    AMC_MEMBER_NOT_FOUND,
    AMC_VKEY_EXPIRED,
    AMC_MAIL_FAILED,
    AMC_PROJECT_DOMAIN_NOT_FOUND,
    AMC_PROJECT_SUB_DOMAIN_NOT_FOUND,
    AMC_UNVERIFIED_ACCOUNT;


    @Override
    public String getMessage() {
        return this.name();
    }

    /**
     * to get the equivalent {@link HttpStatus} of an error.
     *
     * @return {@link HttpStatus}
     */
    public HttpStatus toHttpStatus() {

        switch (this) {
            case AMC_BOOKING_STAND_NOT_FOUND:
            case AMC_EXIHIBITOR_NOT_FOUND:
            case AMC_ACTIVITY_NOT_FOUND:
            case AMC_ACCOUNT_NOT_FOUND:
            case AMC_PLANNING_NOT_FOUND:
            case AMC_ROLE_NOT_FOUND:
            case AMC_COMMISSION_NOT_FOUND:
            case AMC_COMMISSION_ACCOUNT_NOT_FOUND:
            case AMC_PUBLICATION_NOT_FOUND:
            case AMC_BOOKING_SCHOOL_NOT_FOUND:
            case AMC_PUBLISHER_REPRESENTED_NOT_FOUND:
            case AMC_DOCUMENT_NOT_FOUND:
            case AMC_PROJECT_DOMAIN_NOT_FOUND:
            case AMC_ARTIST_ACCOUNT_NOT_FOUND:
            case AMC_PROPOSAL_PROJECT_NOT_FOUND:
            case AMC_PROJECT_SUB_DOMAIN_NOT_FOUND:
            case AMC_BOOK_PERSON_FOUND:
                return HttpStatus.NOT_FOUND;
            case AMC_INVALID_PARAMETER:
            case AMC_TECHNICAL_ERROR:
            case AMC_TIME_FORMAT_INVALID:
            case AMC_ACCOUNT_ALREADY_EXISTS:
            case AMC_INVALID_CODE_OTP:
            case AMC_ARTIST_ACCOUNT_REFERENCE_ALREADY_EXIST:
            case AMC_INVALID_STATUS:
            case AMC_INVALID_ENTITY_NAME:
            case AMC_DUPLICATE_MEMBER:
            case AMC_BOOKING_STAND_EXIST:
            case AMC_INVALID_INSCRIPTION:
            case AMC_BAD_CREDENTIALS:
            case AMC_INVALID_DATE_FOUND:
            case AMC_UNVERIFIED_ACCOUNT:
            case AMC_PERIOD_PLANNING_ERROR:
            case AMC_VKEY_EXPIRED:
            case AMC_OLD_PASSWORD_INVALID:
            case AMC_MAIL_FAILED:
            case AMC_EMAIL_IN_USE:
            case AMC_ROLE_NOT_EDITABLE:
            case AMC_ROLE_NOT_REMOVABLE:
            case AMC_PASSWORD_NOT_CONFIRMED:
            case  AMC_OPEN_EDITION_MUST_BE_CLOSE:
                return HttpStatus.BAD_REQUEST;
            case AMC_UNAUTHENTIFIED:
                return HttpStatus.UNAUTHORIZED;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
