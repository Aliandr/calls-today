package net.zenkevich.alexander.callstoday.validator;

import net.zenkevich.alexander.callstoday.annotation.PhoneRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates that the <code>String</code> length either equal to local length or in range between local length and local plus international part length.
 */
public class PhoneRangeValidator implements ConstraintValidator<PhoneRange, String> {

  private int localPartLength;
  private int fullLength;

  @Override
  public void initialize(PhoneRange constraintAnnotation) {
    this.localPartLength = constraintAnnotation.local();
    this.fullLength = constraintAnnotation.local() + constraintAnnotation.international();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    int length = value.replaceAll("[^0-9]", "").length();
    return length == localPartLength || (length > localPartLength && length <= fullLength);
  }
}
