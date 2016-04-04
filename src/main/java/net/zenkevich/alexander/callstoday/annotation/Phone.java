package net.zenkevich.alexander.callstoday.annotation;

import net.zenkevich.alexander.callstoday.validator.PhoneConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated {@code CharSequence} must must contain only allowed characters:
 * <pre>
 *   '1234567890', '-', '(', ')', '+', ' '
 * </pre>
 * The '+' character allowed only at the beginning.
 * The number of start and end brackets must be the same.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 */
@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

  String message() default "Allowed characters: digits, '-', '(', ')', '+' and space. The '+' character allowed only at the beginning. The number of start and end brackets must be the same.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
