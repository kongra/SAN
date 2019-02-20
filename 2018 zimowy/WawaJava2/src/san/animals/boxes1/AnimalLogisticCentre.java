package san.animals.boxes1;

import san.animals.Animal;
import san.animals.Elephant;

public class AnimalLogisticCentre {

  public static AnimalBox repackIntoNew(AnimalBox animalBox) {
    AnimalBox other = new AnimalBox(); // 1.
    Animal a = animalBox.get();
    other.put(a);
    return other;
  }

  public static void main(String[] args) {
    Elephant e = new Elephant();
    AnimalBox ab1 = new AnimalBox();
    ab1.put(e);

    AnimalBox ab2 = AnimalLogisticCentre.repackIntoNew(ab1);
  }

}
