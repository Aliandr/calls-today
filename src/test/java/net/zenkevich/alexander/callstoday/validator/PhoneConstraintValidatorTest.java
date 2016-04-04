package net.zenkevich.alexander.callstoday.validator;

import net.zenkevich.alexander.callstoday.model.Call;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PhoneConstraintValidatorTest {

  private Validator validator;

  @Before
  public void setUp() throws Exception {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    this.validator = vf.getValidator();
  }

  @Test
  public void validateTest() {
    Call call = new Call("first", "last", "+(420)-111(222) 333");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void validateNoFirstNameTest() {
    Call call = new Call(null, "last", "123456789");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void validateFirstNameTooLargeTest() {
    String first = "1234567890123456789012345678901";
    Call call = new Call(first, "last", "123456789");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(first, violation.getInvalidValue());
    }
  }

  @Test
  public void validateLastNameTooLargeTest() {
    String last = "1234567890123456789012345678901";
    Call call = new Call("first", last, "123456789");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(last, violation.getInvalidValue());
    }
  }

  @Test
  public void validateNoLastNameTest() {
    String last = null;
    Call call = new Call("first", last, "123456789");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(last, violation.getInvalidValue());
    }
  }

  @Test
  public void validateNoPhoneTest() {
    String phone = null;
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhoneNotAllowedCharsTest() {
    String phone = "12345678f";
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 2);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhonePlusInTheEndTest() {
    String phone = "12345678+";
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 2);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhoneBracketsTest() {
    String phone = "(123)(456(789)";
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhoneTooLongTest() {
    String phone = "+420111222333444";
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhoneTooShortTest() {
    String phone = "+420111222333444";
    Call call = new Call("first", "last", phone);
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 1);
    for (ConstraintViolation<Call> violation : violations) {
      assertEquals(phone, violation.getInvalidValue());
    }
  }

  @Test
  public void validatePhoneMultiErrorTest() {
    Call call = new Call("1234567890123456789012345678901", null, "(123)(456(7t9)");
    Set<ConstraintViolation<Call>> violations = this.validator.validate(call);
    assertEquals(violations.size(), 5);
  }

}
