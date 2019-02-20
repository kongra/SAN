package san.animals.boxes;

import san.animals.Bee;
import san.animals.BlackWidow;
import san.animals.Elephant;

public class LogisticCentre {

  public static BeeBox repackIntoNew(BeeBox b) {
    BeeBox other = new BeeBox(); // 1.
    Bee bee = b.get();        // 2.
    other.put(bee);           // 3.
    return other;
  }

  public static BlackWidowBox repackIntoNew(BlackWidowBox b) {
    BlackWidowBox other = new BlackWidowBox(); // 1.
    BlackWidow blackWidow = b.get();        // 2.
    other.put(blackWidow);                  // 3.
    return other;
  }

  public static ElephantBox repackIntoNew(ElephantBox b) {
    ElephantBox other = new ElephantBox(); // 1.
    Elephant elephant = b.get();        // 2.
    other.put(elephant);                // 3.
    return other;
  }

  public static void main(String[] args) {
    Elephant e = new Elephant();
    ElephantBox elephantBox = new ElephantBox();
    elephantBox.put(e);
    ElephantBox elephantBox1 = LogisticCentre.repackIntoNew(elephantBox);

    Bee b = new Bee();
    BeeBox beeBox = new BeeBox();
    beeBox.put(b);
    BeeBox beeBox1 = LogisticCentre.repackIntoNew(beeBox);

  }

}
