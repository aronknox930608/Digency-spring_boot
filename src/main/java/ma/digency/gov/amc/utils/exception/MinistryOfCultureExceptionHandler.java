package ma.digency.gov.amc.utils.exception;

import ma.digency.gov.amc.dto.ResponseErrorDto;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.service.shared.ResourceBundleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MinistryOfCultureExceptionHandler {

    Logger exceptionHandlerLogger = LogManager.getLogger(MinistryOfCultureExceptionHandler.class);

    //private Tracer tracer;

    private final ResourceBundleService bundleService;

    /**
     * Public constructor.
     * <p>
     * To construct a {@link MinistryOfCultureExceptionHandler} with :
     * </p>
     *
     * @param
     * @param bundleService
     */
    @Autowired
    public MinistryOfCultureExceptionHandler(/*Tracer tracer,*/ ResourceBundleService bundleService) {
        super();
        //this.tracer = tracer;
        this.bundleService = bundleService;
    }

    /**
     * To handle all thrown {@link ConstraintViolationException}.
     *
     * @param constraintViolationException
     * @param request
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseErrorDto> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException, WebRequest request) {
        exceptionHandlerLogger.error("ConstraintViolationException Error : ",
                constraintViolationException);
        List<String> validationErrorMessage = constraintViolationException.getConstraintViolations()
                .stream().map(violation -> violation.getRootBeanClass().getName() + " "
                        + violation.getPropertyPath())
                .collect(Collectors.toList());

        ResponseErrorDto errorDto =
                new ResponseErrorDto(MinistryOfCultureMessage.AMC_INVALID_PARAMETER.name(),
                        "Constraint violation", validationErrorMessage, getCorrelationId());
        return new ResponseEntity<>(errorDto,
                MinistryOfCultureMessage.AMC_INVALID_PARAMETER.toHttpStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ResponseErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                            WebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        ResponseErrorDto errorDto = new ResponseErrorDto();
        var amcError = MinistryOfCultureMessage.AMC_INVALID_PARAMETER;
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField()));
        errorDto.setCorrelationId(getCorrelationId());
        errorDto.setCode(amcError.getMessage());
        errorDto.setMessageTemplate(bundleService.getMessage((amcError.getMessage())));
        errorDto.setMessageArguments(errors);

        return new ResponseEntity<>(errorDto, amcError.toHttpStatus());
    }

    /**
     * To handle all thrown {@link MinistryOfCultureBusinessException}.
     *
     * @param ministryOfCultureBusinessException
     * @param request
     * @return
     */
    @ExceptionHandler({MinistryOfCultureBusinessException.class})
    public ResponseEntity<ResponseErrorDto> handleMinistryOfCultureBusinessException(
            MinistryOfCultureBusinessException ministryOfCultureBusinessException, WebRequest request) {
        exceptionHandlerLogger.error("Business Exception : ", ministryOfCultureBusinessException);
        ResponseErrorDto errorDto = null;
        if (ministryOfCultureBusinessException.getMessageParams() == null) {
            errorDto = new ResponseErrorDto(ministryOfCultureBusinessException.getCode(),
                    bundleService.getMessage(ministryOfCultureBusinessException.getCode()), null);
        } else {
            String msg = MessageFormat.format(
                    bundleService.getMessage(ministryOfCultureBusinessException.getCode()),
                    ministryOfCultureBusinessException.getMessageParams());
            errorDto = new ResponseErrorDto(ministryOfCultureBusinessException.getCode(), msg,
                    Arrays.asList(ministryOfCultureBusinessException.getMessageParams()));
        }
        errorDto.setCorrelationId(getCorrelationId());

        return new ResponseEntity<>(errorDto,
                ((MinistryOfCultureMessage) ministryOfCultureBusinessException.getError()).toHttpStatus());
    }


    /**
     * Handle all other exceptions {@link Exception}
     *
     * @param ministryOfCultureBusinessException
     * @param request
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseErrorDto> handleMinistryOfCultureException(
            Exception ministryOfCultureBusinessException, WebRequest request) {
        exceptionHandlerLogger.error("Technical Error : ", ministryOfCultureBusinessException);
        ResponseErrorDto errorDto =
                new ResponseErrorDto(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR.name(),
                        bundleService.getMessage(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR.name()), null);
        errorDto.setCorrelationId(getCorrelationId());
        return new ResponseEntity<>(errorDto,
                MinistryOfCultureMessage.AMC_TECHNICAL_ERROR.toHttpStatus());
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseErrorDto> handleBadCredentialsException(
            Exception ministryOfCultureBusinessException, WebRequest request) {
        exceptionHandlerLogger.error("Credential Error : ", ministryOfCultureBusinessException);
        ResponseErrorDto errorDto =
                new ResponseErrorDto(MinistryOfCultureMessage.AMC_BAD_CREDENTIALS.name(),
                        bundleService.getMessage(MinistryOfCultureMessage.AMC_BAD_CREDENTIALS.name()), null);
        errorDto.setCorrelationId(getCorrelationId());
        return new ResponseEntity<>(errorDto,
                MinistryOfCultureMessage.AMC_BAD_CREDENTIALS.toHttpStatus());
    }


    /**
     * Handle all InvocationTarget exceptions {@link InvocationTargetException}.
     *
     * @param iTException
     * @param request
     * @return
     */
    @ExceptionHandler({InvocationTargetException.class})
    public ResponseEntity<ResponseErrorDto> handleInvocationTargetException(
            InvocationTargetException iTException, WebRequest request) {
        exceptionHandlerLogger.error("InvocationTargetException Error : ", iTException);
        if (iTException.getCause() instanceof MinistryOfCultureBusinessException) {
            return handleMinistryOfCultureBusinessException(
                    (MinistryOfCultureBusinessException) iTException.getCause(), request);
        } else {
            return handleMinistryOfCultureException((Exception) iTException.getCause(), request);
        }
    }


    private String getCorrelationId() {
        return "";/*null != tracer && null != tracer.currentSpan() && null != tracer.currentSpan().context()
        ? tracer.currentSpan().context().traceIdString()
        : "";*/
    }

}
