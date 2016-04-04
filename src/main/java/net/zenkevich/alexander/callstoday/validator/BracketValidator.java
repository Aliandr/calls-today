package net.zenkevich.alexander.callstoday.validator;

import net.zenkevich.alexander.callstoday.annotation.Brackets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Stack;

/**
 * Validates that the <code>String</code> contain the same number of start and end brackets.
 */
public class BracketValidator implements ConstraintValidator<Brackets, String> {
  @Override
  public void initialize(Brackets constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    HashMap<Character, Character> map = new HashMap<>();
    map.put('(', ')');

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < value.length(); i++) {
      char curr = value.charAt(i);

      if (map.keySet().contains(curr)) {
        stack.push(curr);
      } else if (map.values().contains(curr)) {
        if (!stack.empty() && map.get(stack.peek()) == curr) {
          stack.pop();
        } else {
          return false;
        }
      }
    }

    return stack.empty();
  }
}
