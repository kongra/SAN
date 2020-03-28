public class Viper implements Animal {

  @Override
  public void eat(Object food) {
    System.out.println("Digesting:" + food);
  }

  @Override
  public void sleep() {
    System.out.println("Viper sleeps");
  }
}
