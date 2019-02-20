package nonstattionary.math;

public class TestStringBuilder {

  public static void main(String... args) {
    String s = String.valueOf(1001);
    StringBuilder buf = new StringBuilder(s).reverse();

    System.out.println(buf.equals(s));
  }

}
