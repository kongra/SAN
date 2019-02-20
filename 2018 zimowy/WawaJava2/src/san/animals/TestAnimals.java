package san.animals;

public class TestAnimals {

  public static void main(String[] args) {
    Elephant e1 = new Elephant();
    Animal a1 = e1;

    e1.startMoving();
    a1.stopMoving();

    Animal a2 = new Bee();
    a2.feedYourself("...");
    a1.feedYourself("...");
  }

}
