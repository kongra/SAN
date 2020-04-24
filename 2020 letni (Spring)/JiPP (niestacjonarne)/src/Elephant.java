public class Elephant implements Animal {

  @Override
  public void eat(Food food) {
    System.out.println("Jumbo happy eating " + food);
  }

  @Override
  public void sleep() {
    System.out.println("Jumbo sleeps.");
  }

  @Override
  public void runLifeCycle(Food defaultFood) {
    while(true) {
      eat(defaultFood);
      sleepActually(3000);
      sleep();
      sleepActually(3000);
    }
  }
}
