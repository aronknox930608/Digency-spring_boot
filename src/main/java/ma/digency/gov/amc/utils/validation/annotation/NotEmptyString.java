package ma.digency.gov.amc.utils.validation.annotation;

import ma.digency.gov.amc.utils.validation.validator.StringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StringValidator.class)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotEmptyString {
    String message() default "must not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
