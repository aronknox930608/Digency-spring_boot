package ma.digency.gov.amc.utils.validation.validator;

import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<NotEmptyString,String > {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return true;
        return StringUtils.isNotBlank(s);
    }
}
