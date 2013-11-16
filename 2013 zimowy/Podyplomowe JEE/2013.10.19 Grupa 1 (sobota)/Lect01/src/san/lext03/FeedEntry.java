package san.lext03;

public enum FeedEntry {

  HIGH("wlot A") {
    @Override
    public double throughput() {
      return 0.25;
    }
  },
  MID("wlot B") {
    @Override
    public double throughput() {
      return 0.38;
    }
  },
  LOW("wlot C") {
    @Override
    public double throughput() {
      return 0.27;
    }
  };

  private final String description;

  private FeedEntry(String description) {
    this.description = description;
  }

  public String description() {
    return this.description;
  }

  public abstract double throughput();
}
