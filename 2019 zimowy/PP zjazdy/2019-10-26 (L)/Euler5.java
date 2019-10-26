class Euler5 {

  static boolean divisibleBy(int n, int from, int to) {
    int i = from;
    while(i <= to) {
      if ((n % i) != 0) {
        return false;
      }
      i = i + 1;
    }
    return true;
  }

  public static void main(String[] args) {
    int n = 1;
    boolean end = false;

    while(!end) {
      if(divisibleBy(n, 1, 20)) {
        System.out.println("Result is " + n);
        end = true;
      }
      n = n + 1;
    }
  }

}
