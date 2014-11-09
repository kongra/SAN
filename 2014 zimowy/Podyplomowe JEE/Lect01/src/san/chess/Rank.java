package san.chess;

public enum Rank {
  ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

  private final int repr;

  private Rank(int repr) {
    this.repr = repr;
  }

  @Override
  public String toString() {
    return String.valueOf(repr);
  }

  
}
