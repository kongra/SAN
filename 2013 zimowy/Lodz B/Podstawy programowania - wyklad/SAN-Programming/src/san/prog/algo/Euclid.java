package san.prog.algo;

public class Euclid {

  public static long gcd(long m, long n) {
    long r = m % n;
    return r == 0 ? n : gcd(n, r);
  }

}
