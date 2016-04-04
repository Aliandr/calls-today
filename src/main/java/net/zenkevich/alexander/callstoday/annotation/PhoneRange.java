package net.zenkevich.alexander.callstoday.annotation;

import net.zenkevich.alexander.callstoday.validator.PhoneRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;

/**
 * The annotated {@code CharSequence} should be ether 9 digits without international code or not more than 14 digits with international code.
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 */
@Documented
@Constraint(validatedBy = PhoneRangeValidator.class)
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneRange {

  int local() default 9;

  int international() default 5;

  String message() default "Phone number should be either 9 digits without international code or not more than 14 digits with international code";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


}
