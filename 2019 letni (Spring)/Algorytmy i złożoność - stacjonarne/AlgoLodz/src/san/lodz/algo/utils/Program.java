package san.lodz.algo.utils;

public class Program {

  public static void main(String... args) {
    Seq<String> napisy =
        LinkedSeq.make("abc", "wa", "qwerty");

    System.out.println(napisy.asString());
    System.out.println(Nil.get().asString());

    System.out.println(napisy.indexOf(s -> "b".equals(s)));
    System.out.println(napisy.findFirst(s -> s.length() == 2));

    System.out.println(LinkedSeq.make(1, 2, 3, 4, 5).
        fmap(n -> n * n).asString());
  }

}
