package san.animals.boxes;

import san.animals.Elephant;

public class ElephantBox {

  private Elephant obj;

  public Elephant get() {
    Elephant e = this.obj;
    this.obj = null;
    return e;
  }

  public void put(Elephant obj) {
    this.obj = obj;
  }
}
