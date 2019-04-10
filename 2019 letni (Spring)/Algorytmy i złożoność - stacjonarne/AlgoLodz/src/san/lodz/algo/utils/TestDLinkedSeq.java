package san.lodz.algo.utils;

public class TestDLinkedSeq {

  public static void main(String... args) {
    DLinkedSeq<String> s = new DLinkedSeq<>();
    System.out.println(s.asString());

    s.addHead("a");
    System.out.println(s.asString());

    s.addTail("b");
    s.addTail("c");
    s.addTail("d");

    System.out.println(s.head());
    System.out.println(s.tail());
    System.out.println(s.asString());
  }

}
