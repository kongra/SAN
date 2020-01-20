package san.pp.biology;

public abstract class Mammal {

  boolean producesMilk;

  String repr() {
    return "Mammal.producesMilk = " +
        producesMilk;
  }
}
