public class Refs {

  static void foo(int n) {
    System.out.println("foo:n " + n);
    n = 5;
    System.out.println("foo:n " + n);
  }

  static void goo(int[] ns) {
    System.out.println("goo:ns " + ns[0]);
    //ns = new int[]{4, 5, 6};
    ns[0] = 8;
    System.out.println("goo:ns " + ns[0]);
  }

  public static void main(String[] args) {
    int[] ms = {1, 2, 3};
    System.out.println("main:ms " + ms[0]);
    goo(ms);
    System.out.println("main:ms " + ms[0]);

//    int m = 3;
//    System.out.println("main:m " + m);
//    foo(m);
//    System.out.println("main:m " + m);


  }

}
