package san.lodz.algo.utils;

public class Program {

  public static void main(String... args) {
    Seq<String> napisy =
        LinkedSeq.make("a", "b", "c");

    System.out.println(napisy.asString());
    System.out.println(Nil.get().asString());
  }

}
