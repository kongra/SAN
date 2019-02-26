package nonstattionary.lect20190127;

public class TestAnimal {

  public static void main(String... args) {
    Animal a = new Mammal();
    System.out.println(a.isMoving());
    a.eat(new Milk());
    // ...
    a.eat(new Grass());
  }

}
