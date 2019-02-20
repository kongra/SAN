package san.animals;

public abstract class Animal {

  private boolean isMoving;

  public void startMoving() {
    this.isMoving = true;
  }

  public void stopMoving() {
    this.isMoving = false;
  }

  public abstract void feedYourself(Object food);

}
