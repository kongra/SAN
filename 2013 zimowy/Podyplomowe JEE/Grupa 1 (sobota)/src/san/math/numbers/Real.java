package san.math.numbers;

public abstract class Real extends Complex {

  public static Real valueOf(final double d) {
    return new Real() {

      @Override
      public double doubleValue() {
        return d;
      }

      @Override
      public Num re() {
        return this;
      }

      @Override
      public Num im() {
        return Real.valueOf(0);
      }

    };
  }

  public abstract double doubleValue();

  @Override
  public Num add(Complex other) {
    return other.add(this);
  }

  @Override
  public Num add(Real other) {
    return Real.valueOf(this.doubleValue() + other.doubleValue());
  }

  @Override
  public Num add(Int other) {
    return Real.valueOf(this.doubleValue() + other.doubleValue());
  }

  @Override
  public boolean isZero() {
    // OUCH!!!
    return doubleValue() == 0.0;
  }

  @Override
  public String toString() {
    return String.valueOf(doubleValue());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(doubleValue());
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    if (!(obj instanceof Real)) {
      return false;
    }
    Real other = (Real) obj;
    if (Double.doubleToLongBits(doubleValue()) != Double.doubleToLongBits(other
        .doubleValue())) {
      return false;
    }
    return true;
  }

}
