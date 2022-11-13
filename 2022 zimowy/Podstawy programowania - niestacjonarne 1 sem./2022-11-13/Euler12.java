class Euler12 {

  static int countFactors(long n) {
    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (isFactor(i, n)) {
        count += 1;
      }
    }
    return count;
  }

  static boolean isFactor(int i, long n) {
    return n % i == 0;
  }

  public static void main(String... args) {
    long triangleNumber = 0;
    for (long n = 1; n <= 7000; n++) {
      triangleNumber += n;
      int count = countFactors(triangleNumber);            
      
      if (count > 200) {
        System.out.println(n + "-th triangle number " + triangleNumber + 
          " has " + count + " factors");
      }

      if (count > 500) {        
        break;
      }
    }
  }

}