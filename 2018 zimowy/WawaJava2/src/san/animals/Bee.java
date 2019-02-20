package san.animals;

public class Bee extends Insect {

  @Override
  public void feedYourself(Object food) {
    System.out.println("Eating pollen and nectar.");
  }

  @Override
  public void startMoving() {
    System.out.println("Starting a flight.");
    super.startMoving();
  }
}
