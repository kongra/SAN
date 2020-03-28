public class Elephant implements Animal {

  @Override
  public void eat(Object food) {
    System.out.println("Jumbo happy eating " + food);
  }

  @Override
  public void sleep() {
    System.out.println("Jumbo sleeps.");
  }
}
