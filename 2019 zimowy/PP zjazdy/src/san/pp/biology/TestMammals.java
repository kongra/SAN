package san.pp.biology;

public class TestMammals {

  public static void main(String[] args) {
    Mammal m1 = new Mammal();
    System.out.println(m1.repr());

    Elephant e1 = new Elephant();
    e1.producesMilk = true;
    System.out.println(e1.repr());
  }

}
