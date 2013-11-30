package san.math.numbers;

public abstract class Int extends Real {

  public static Int valueOf(final int i) {
    return new Int() {

      @Override
      public int intValue() {
        return i;
      }

      @Override
      public double doubleValue() {
        return i;
      }

      @Override
      public Num re() {
        return this;
      }

      @Override
      public Num im() {
        return Int.valueOf(0);
      }

    };
  }

  public abstract int intValue();

  @Override
  public Num add(Complex other) {
    return other.add(this);
  }

  @Override
  public Num add(Real other) {
    return other.add(this);
  }

  @Override
  public Num add(Int other) {
    return Int.valueOf(this.intValue() + other.intValue());
  }

  @Override
  public boolean isZero() {
    return intValue() == 0;
  }

  @Override
  public String toString() {
    return String.valueOf(intValue());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + intValue();
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
    if (!(obj instanceof Int)) {
      return false;
    }
    Int other = (Int) obj;
    if (intValue() != other.intValue()) {
      return false;
    }
    return true;
  }

}
