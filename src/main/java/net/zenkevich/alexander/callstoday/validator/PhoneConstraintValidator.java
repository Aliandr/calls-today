package net.zenkevich.alexander.callstoday.validator;

import net.zenkevich.alexander.callstoday.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates that the <code>String</code> contain only allowed characters:
 * <pre>
 *  '1234567890', '-', '(', ')', '+', ' '
 * </pre>
 * The '+' character allowed only at the beginning.
 */
public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {

  @Override
  public void initialize(Phone phone) {
  }

  @Override
  public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
    if (phoneField == null) {
      return true;
    }
    return phoneField.matches("^\\+?[-() \\d]+");
  }

}
