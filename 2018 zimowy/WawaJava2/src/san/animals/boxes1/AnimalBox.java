package san.animals.boxes1;

import san.animals.Animal;

public class AnimalBox {

  private Animal obj;

  public Animal get() {
    Animal a = this.obj;
    this.obj = null;
    return a;
  }

  public void put(Animal obj) {
    this.obj = obj;
  }

}
