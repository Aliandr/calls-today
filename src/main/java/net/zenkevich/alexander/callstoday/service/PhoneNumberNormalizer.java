package net.zenkevich.alexander.callstoday.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.substring;

/**
 * Phone number normalizer
 */
@Component
public class PhoneNumberNormalizer {

  public static final String DEFAULT_CODE = "00420";

  /**
   * <p>Number will be normalized into the uniform format '00YYY XXX XXX XXX',
   * where the X block is the local part and the Y block is the international part.
   * If no international part is specified on input, international area code 420 will be added automatically.</p>
   * <p>The + character at the beginning is converted to 00.
   * All non digit characters will be removed.</p>
   * <pre>
   *  "123456789"          -> "00420 111 222 333"
   *  "00420111222333"     -> "00420 111 222 333"
   *  "+(420)-111 222-333" -> "00420 111 222 333"
   * </pre>
   *
   * @param phone string to normalize
   * @return normalized string
   * @throws NullPointerException     if phone number is null or blank
   * @throws IllegalArgumentException if phone number is less then 9 digits
   */
  public String normalize(String phone) {
    if (StringUtils.isBlank(phone)) {
      throw new NullPointerException("Phone number should not be empty");
    }
    String result = removeSpecialChars(phone);
    if (result.length() < 9) {
      throw new IllegalArgumentException("Phone number should be at least 9 digits");
    }
    result = addCode(result);
    return format(result);
  }

  private String removeSpecialChars(String phone) {
    return phone.replaceAll("[^0-9]", "");
  }

  private String format(String phone) {
    return substring(phone, 0, 5) + SPACE
        + substring(phone, 5, 8) + SPACE
        + substring(phone, 8, 11) + SPACE
        + substring(phone, 11, 14);
  }

  private String addCode(String phone) {
    if (phone.length() == 9) {
      return DEFAULT_CODE + phone;
    } else {
      String number = StringUtils.right(phone, 9);
      String code = StringUtils.substringBefore(phone, number);
      return StringUtils.leftPad(code, 5, '0') + number;
    }
  }

}
