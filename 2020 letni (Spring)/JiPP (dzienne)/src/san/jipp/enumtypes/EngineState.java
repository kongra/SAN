package san.jipp.enumtypes;

public enum EngineState {
    OFF       (0)
  , LOW_SPEED (10)
  , FULL_LOAD (100)
  , BOOSTED   (120)
  , EXTRA_LOAD(150);

    static {
      OFF.next       = LOW_SPEED;
      LOW_SPEED.next = FULL_LOAD;
      FULL_LOAD.next = BOOSTED;
      BOOSTED.next   = EXTRA_LOAD;
    }

  private final double emission;

  private EngineState next;

  private EngineState prev;

  EngineState(double emission) {
    this.emission = emission;
  }

  public EngineState next() {
    return next;
  }

  public double currentEmission() {
    return emission;
//    if (this ==       OFF) return 0;
//    if (this == LOW_SPEED) return 10;
//    if (this == FULL_LOAD) return 100;
//
//    throw new IllegalArgumentException("Can't handle " + this);
  }
}
