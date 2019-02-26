package san.animals.boxes;

import san.animals.Bee;

public class BeeBox {

  private Bee obj;

  public Bee get() {
    Bee b = this.obj;
    this.obj = null;
    return b;
  }

  public void put(Bee obj) {
    this.obj = obj;
  }
}
