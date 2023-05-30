package ma.digency.gov.amc.utils.validation.validator;

import ma.digency.gov.amc.utils.validation.annotation.Isbn;
import org.apache.commons.validator.routines.ISBNValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return false;
        return ISBNValidator.getInstance().isValid(s);
    }
}
