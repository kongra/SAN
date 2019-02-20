package san.animals.boxes;

import san.animals.BlackWidow;

public class BlackWidowBox {

  private BlackWidow obj;

  public BlackWidow get() {
    BlackWidow bw = this.obj;
    this.obj = null;
    return bw;
  }

  public void put(BlackWidow obj) {
    this.obj = obj;
  }
}
