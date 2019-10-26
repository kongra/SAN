class Euler6 {

  public static void main(String[] args) {
    int n = 1;
    int sum = 0;
    int sumOfSquares = 0;
    while(n <= 100) {
      sumOfSquares = sumOfSquares + (n * n);
      sum = sum + n;
      n = n + 1;
    }

    int squareOfSum = sum * sum;
    int result = squareOfSum - sumOfSquares;
    System.out.println("Wynik " + result);
  }

}
