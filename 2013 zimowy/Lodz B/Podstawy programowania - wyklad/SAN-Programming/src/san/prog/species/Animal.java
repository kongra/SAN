package san.prog.species;

public class Animal {

  private boolean living;

  public Animal() {
    living = true;
  }

  public boolean isLiving() {
    return living;
  }

  public void die() {
    living = false;
  }

}
