package net.zenkevich.alexander.callstoday.annotation;

import net.zenkevich.alexander.callstoday.validator.BracketValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

/**
 * The annotated {@code CharSequence} must must contain the same number of start and end brackets.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 */
@Documented
@Constraint(validatedBy = BracketValidator.class)
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Brackets {

  String message() default "The number of start and end brackets must be the same.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
