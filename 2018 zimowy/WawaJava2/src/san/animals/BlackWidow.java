package san.animals;

public class BlackWidow extends Arachnid {

  @Override
  public void feedYourself(Object food) {
    System.out.println("Eating small arthropods");
  }

  @Override
  public void startMoving() {
    System.out.println("Starting a run.");
    super.startMoving();
  }
}
