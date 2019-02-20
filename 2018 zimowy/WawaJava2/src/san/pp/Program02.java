package san.pp;

public class Program02 {

  static int fact(int n) {
    if (n == 0) {
      return 1;
    }
    int result = 1;
    int i = 1;
    while (i <= n) {
      result = result * i;
      i = i + 1;
    }
    return result;
  }

  static int factorial(int n) {
    if (n == 0) {
      return 1;
    } else {
      return n * factorial(n-1);
    }
  }

  public static void main(String[] args) {
    System.out.println(fact(0));
    System.out.println(fact(1));
    System.out.println(fact(2));
    System.out.println(fact(3));
    System.out.println(fact(4));
    System.out.println(fact(5));
    System.out.println(fact(6));

    System.out.println(factorial(0));
    System.out.println(factorial(1));
    System.out.println(factorial(2));
    System.out.println(factorial(3));
    System.out.println(factorial(4));
    System.out.println(factorial(5));
    System.out.println(factorial(6));
  }

}
