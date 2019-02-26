package san.utils;

public class Test {

  public static void main(String[] args) {
    Seq<String> l1 = new LinkedSeq<>("a", null);
    Seq<String> l2 = l1.cons("b");

    Seq<String> l3 = LinkedSeq.of("a", "b", "c");
    System.out.println(l3);
  }

}
