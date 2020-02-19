package san.jipp;

import san.jipp.types.Age;

import java.util.Optional;

public class Main {

//  static Optional<Age> getAge(String email) {
//    //
//  }

  static void printAge(Age age) {
    System.out.println(age);
  }

  public static void main(String[] args) {
    Optional<Age> age1 = Age.of(-1);
    if (!age1.isEmpty()) {
      printAge(age1.get());
    }

    age1.map(age -> {
      printAge(age);
      return null;
    });

    // System.out.println(age1);
  }

}
