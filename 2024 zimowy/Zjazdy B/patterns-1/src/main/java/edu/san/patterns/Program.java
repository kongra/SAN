package edu.san.patterns;

class Program {

  public static void main(String... args) {
//    var o1 = Singleton.getInstance();
//    var o2 = Singleton.getInstance();
//
//    System.out.println(o1 == o2);
//    System.out.println(o1.equals(o2));

//    if (args != null && args.length == 10) {
//      var o1 = Singleton.getInstance();
//      System.out.println(o1);
//    }


    var o1 = Singleton.getInstance();
    System.out.println(o1);
    System.out.println("Zakończyłem");
  }
}