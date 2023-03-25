// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.animals;

public class Zebra extends Animal {

  @Override
  public void eat(String food) {
    System.out.println("Zebra::eat " + food);
  }

}
