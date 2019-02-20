package nonstattionary.lect20190127;

public class Mammal extends Animal {

  @Override
  public void eat(Food food) {
    if (food instanceof  Milk)
      System.out.println("I'm drinking " + food);
    else
      super.eat(food);
  }

}
