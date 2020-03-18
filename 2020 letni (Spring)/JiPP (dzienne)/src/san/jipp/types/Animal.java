package san.jipp.types;

import java.time.LocalDate;

public abstract class Animal {

  private final LocalDate birthDate = LocalDate.now();

  public abstract void eat(Object food);

  public int ageInYears() {
    var currentYear = LocalDate.now().getYear();
    return currentYear - birthDate.getYear();
  }

  //

}
