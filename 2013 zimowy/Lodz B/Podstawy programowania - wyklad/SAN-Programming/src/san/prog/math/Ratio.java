package san.prog.math;

public class Ratio {

  public static long gcd(long m, long n) {
    while (true) {
      long r = m % n;
      if (r == 0) {
        return n;
      }
      m = n;
      n = r;
    }
  }

  public final long m;

  public final long n;

  public Ratio(long m, long n) {
    long p = gcd(m, n);
    this.m = m / p;
    this.n = n / p;
  }

  public Ratio add(Ratio other) {
    long m = this.m * other.n + this.n * other.m;
    long n = this.n * other.n;
    return new Ratio(m, n);
  }

  @Override
  public String toString() {
    if (this.n == 1) {
      return String.valueOf(this.m);
    }

    return this.m + "/" + this.n;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (m ^ (m >>> 32));
    result = prime * result + (int) (n ^ (n >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Ratio other = (Ratio) obj;
    if (m != other.m) {
      return false;
    }
    if (n != other.n) {
      return false;
    }
    return true;
  }

}
