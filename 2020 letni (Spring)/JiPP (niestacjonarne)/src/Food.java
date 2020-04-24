public enum Food {
    GRASS(100)
  , MEAT (200)
  , BREAD(500);

  private final double calories;

  Food(double calories) {
    this.calories = calories;
  }

  public double calories() {
    return calories;
  }
}
