// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.animals;

public class Elephant extends Animal {

  @Override
  public void eat(String food) {
    System.out.println("Elephant::eat " + food);
  }

}
