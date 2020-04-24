package san.jipp.funprog;

import java.util.Arrays;
import java.util.List;

public class TestStringWorker {

  private static class LengthWorker implements StringWorker {
    @Override
    public int work(String s) {
      return s == null ? 0 : s.length();
    }
  }

  public static void main(String... args) {
    List<String> strs = Arrays.asList("abcd", "xayt", "www", "rts");
    List<Integer> ints = StringTools.transform(
        s -> s == null ? 0 : s.length()
        , strs);

    System.out.println(ints);
    System.out.println(StringTools.transform(
        s -> s.indexOf('c'), strs));

    // StringWorker worker = new LengthWorker();
//    List<Integer> ints = StringTools.transform(
//        new StringWorker() {
//          @Override
//          public int work(String s) {
//            return s == null ? 0 : s.length();
//          }
//        }, strs);

  }

}
