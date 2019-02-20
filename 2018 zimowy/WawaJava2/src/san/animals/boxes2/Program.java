package san.animals.boxes2;

import san.animals.BlackWidow;
import san.animals.Elephant;

public class Program {

  public static void main(String[] args) {
    Box<Elephant> elephantBox = new Box<>();
    elephantBox.put(new Elephant());

    Box<BlackWidow> blackWidowBox = new Box<>();
    blackWidowBox.put(new BlackWidow());

    Box<Elephant> elephantBox1 = GenericLogisticCentre.repackIntoNew(elephantBox);

    Box<BlackWidow> blackWidowBox1 = GenericLogisticCentre.repackIntoNew(blackWidowBox);

    Box<String> sbox = new Box<>();
    sbox.put("abcd");
    Box<String> sbox1 = GenericLogisticCentre.repackIntoNew(sbox);

  }

}
