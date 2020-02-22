package san.jipp.types;

import java.util.Optional;

public class Age {

  public static Optional<Age> of(int value) {
    if (value < 0 || value > 140) {
      return Optional.empty();
    }
    return Optional.of(new Age(value));
  }

  private final int value;

  private Age(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
