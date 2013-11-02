package san.coll;

public class Utils {

  public static void doSeq(final ISeq coll, final Unary body) {
    ISeq s = coll;
    while (!s.isEmpty()) {
      body.call(s.first());
      s = s.rest();
    }
  }

  private Utils() {
    ;
  }

}
