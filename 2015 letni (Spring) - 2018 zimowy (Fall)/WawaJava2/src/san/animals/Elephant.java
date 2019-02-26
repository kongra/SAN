package san.animals;

public class Elephant extends Mammal {

  @Override
  public void feedYourself(Object food) {
    System.out.println("Eating large amount of plant food.");
  }

  @Override
  public void startMoving() {
    System.out.println("Starting a slow movement.");
    super.startMoving();
  }

}
