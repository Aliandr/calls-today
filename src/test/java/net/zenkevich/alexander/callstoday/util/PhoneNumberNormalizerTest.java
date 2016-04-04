package net.zenkevich.alexander.callstoday.util;

import net.zenkevich.alexander.callstoday.Application;
import net.zenkevich.alexander.callstoday.service.PhoneNumberNormalizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PhoneNumberNormalizerTest {

  private static final String EXPECTED = "00420 111 222 333";

  @Autowired
  private PhoneNumberNormalizer normalizer;

  @Test
  public void normalizeSimpleTest() {
    String normalized = normalizer.normalize("111222333");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals(EXPECTED, normalized);
  }

  @Test
  public void normalizeWithBracketsTest() {
    String normalized = normalizer.normalize("(111) 222 (333)");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals(EXPECTED, normalized);
  }

  @Test
  public void normalizeWithCodeAndZeroTest() {
    String normalized = normalizer.normalize("00420111222333");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals(EXPECTED, normalized);
  }

  @Test
  public void normalizeWithCodeAndPlusTest() {
    String normalized = normalizer.normalize("+(420) 111 222333");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals(EXPECTED, normalized);
  }

  @Test
  public void normalizeWithDashTest() {
    String normalized = normalizer.normalize("+(420) 111 222333");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals(EXPECTED, normalized);
  }

  @Test
  public void normalizeWithCodeOneDigitTest() {
    String normalized = normalizer.normalize("+(1) (111 222(333))");
    assertNotNull("Normalized phone should not be null", normalized);
    assertEquals("00001 111 222 333", normalized);
  }

  @Test(expected = NullPointerException.class)
  public void normalizeNullPhoneTest() {
    normalizer.normalize(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void normalizeToShortPhoneTest() {
    normalizer.normalize("123");
  }

}
