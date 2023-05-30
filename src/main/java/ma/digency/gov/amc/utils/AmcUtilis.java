package ma.digency.gov.amc.utils;

import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Set;

public class AmcUtilis {

    private static Validator validator;

    public static boolean isValidIsbn(String isbn){
        if(isbn == null)
            return false;
        return ISBNValidator.getInstance().isValid(isbn);
    }

    public static boolean isValidAlphanumeric(String str){
        try {
            if(str != null)
                Integer.valueOf(str);
            return false;
        }catch (Exception e){
            return true;
        }
    }

    /**
     * private constructor.
     */
    private AmcUtilis() {
        super();
    }

    public static <T> void checkBeanValidation(T beanToValidate) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the Java bean and throw an excetion if any constraint has been violated
        Set<ConstraintViolation<T>> violations = validator.validate(beanToValidate);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


}
