// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

import java.util.Objects;

/**
 * This class (record) is an example of Decorator.
 */
public record CountableStringDecorator(String value) implements Countable {
  
  public static CountableStringDecorator of(String value) {
    return new CountableStringDecorator(value);
  }
  
  public CountableStringDecorator {
    Objects.requireNonNull(value);
  }

  @Override
  public int count() {
    return value.length();
  }

}
