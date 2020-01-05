package san.pp.biology;

public class Elephant extends Mammal {

  boolean hasTrunk = true;

  boolean hasTusks = true;

  @Override
  String repr() {
    return "Elephant.producesMilk = " +
        producesMilk;
  }
}
