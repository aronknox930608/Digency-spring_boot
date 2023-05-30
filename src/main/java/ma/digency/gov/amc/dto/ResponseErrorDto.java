package ma.digency.gov.amc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public class ResponseErrorDto implements Serializable {

    public static final String ERROR_DTO_MODEL_NAME = "ResponseErrorDto";

    /**
     * generated serial version ID
     */
    private static final long serialVersionUID = 8777126824071750555L;

    @Schema(description = "Code de l'erreur", required = true, example = "ERROR_CODE")
    private String code;

    @Schema(description = "Modèle utilisé pour le message d'erreur", required = false,
            example = "Error Message template [%s]")
    private String messageTemplate;

    @Schema(description = "Les arguments du message", required = false, example = "[XXX_01_00000001]")
    private List<String> messageArguments;

    @Schema(description = "Liste des sous-erreurs", required = false)
    private List<String> subErrors;

    @Schema(description = "Identifiant de correlation", required = false,
            example = "e84211f588db92df")
    private String correlationId;

    /**
     * Public constructor.
     * <p>
     * To construct a {@link ResponseErrorDto} with :
     * </p>
     */
    public ResponseErrorDto() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * Public constructor.
     * <p>
     * To construct a {@link ResponseErrorDto} with :
     * </p>
     *
     * @param code
     * @param messageTemplate
     * @param messageArguments
     * @param subErrors
     * @param correlationId
     */
    public ResponseErrorDto(String code, String messageTemplate, List<String> messageArguments,
                            List<String> subErrors, String correlationId) {
        super();
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.messageArguments = messageArguments;
        this.subErrors = subErrors;
        this.correlationId = correlationId;
    }


    /**
     * Public constructor.
     * <p>
     * To construct a {@link ResponseErrorDto} with :
     * </p>
     *
     * @param code
     * @param messageTemplate
     * @param messageArguments
     */
    public ResponseErrorDto(String code, String messageTemplate, List<String> messageArguments) {
        super();
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.messageArguments = messageArguments;
    }


    /**
     * Public constructor.
     * <p>
     * To construct a {@link ResponseErrorDto} with :
     * </p>
     *
     * @param code
     * @param messageTemplate
     * @param messageArguments
     * @param correlationId
     */
    public ResponseErrorDto(String code, String messageTemplate, List<String> messageArguments,
                            String correlationId) {
        super();
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.messageArguments = messageArguments;
        this.correlationId = correlationId;
    }


    /**
     * Public getter of the field code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Public Setter of the field code.
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Public getter of the field messageTemplate.
     *
     * @return the messageTemplate
     */
    public String getMessageTemplate() {
        return messageTemplate;
    }

    /**
     * Public Setter of the field messageTemplate.
     *
     * @param messageTemplate the messageTemplate to set
     */
    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    /**
     * Public getter of the field messageArguments.
     *
     * @return the messageArguments
     */
    public List<String> getMessageArguments() {
        return messageArguments;
    }

    /**
     * Public Setter of the field messageArguments.
     *
     * @param messageArguments the messageArguments to set
     */
    public void setMessageArguments(List<String> messageArguments) {
        this.messageArguments = messageArguments;
    }

    /**
     * Public getter of the field errors.
     *
     * @return the errors
     */
    public List<String> getSubErrors() {
        return subErrors;
    }

    /**
     * Public Setter of the field errors.
     *
     * @param errors the errors to set
     */
    public void setSubErrors(List<String> errors) {
        this.subErrors = errors;
    }

    /**
     * Public getter of the field correlationId.
     *
     * @return the correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Public Setter of the field correlationId.
     *
     * @param correlationId the correlationId to set
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "ResponseErrorDto [code=" + code + ", message="
                + String.format(messageTemplate, messageArguments) + ", subErrors=" + subErrors
                + ", correlationId=" + correlationId + "]";
    }

}
