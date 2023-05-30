package ma.digency.gov.amc.service.shared.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(value = { ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DocumentField {

	/**
	 * FieldName(s) in which the attribute should be mapped.
	 */
	String[] fieldName() default "";

	/**
	 * If greater than zero, map the attribute in fieldName1, fieldName2...fieldNameN
	 */
	int iterate() default 0;

	boolean deleteParentIfNull() default false;

	/**
	 * If {@link #iterate()} greater than zero, map the attribute in fieldName[iterateStart], fieldName[iterateStart
	 * +1]...fieldName[iterateStart + iterate]
	 */
	int iterateStart() default 0;

	/**
	 * tells if the field is mandatory
	 */
	boolean isMandatory() default true;
}
