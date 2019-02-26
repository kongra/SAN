package nonstattionary.lect20190127;

public class Animal {

  private boolean moving;

  public boolean isMoving() {
    return moving;
  }

  public void eat(Food food) {
    System.out.println("I'm eating " + food);
  }
}
