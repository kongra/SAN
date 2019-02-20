package san.poly.orchestra;

public class Program {

  static void foo(Object o) {
    System.out.println(o.toString());
  }

  public static void main(String[] args) {
    foo(new Object());
    foo("abc");
    foo(5);
    foo(3.14);
  }

}
