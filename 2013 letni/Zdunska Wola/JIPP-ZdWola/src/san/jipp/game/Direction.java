package san.jipp.game;

public enum Direction {

  LEFT("lewo") {
    @Override
    public void go() {
      System.out.println("Jadę w lewo.");
    }
  },

  RIGHT("prawo") {
    @Override
    public void go() {
      System.out.println("Jadę w prawo.");
    }
  },

  UP("góra") {
    @Override
    public void go() {
      System.out.println("Jadę w górę.");
    }
  },

  DOWN("dół") {
    @Override
    public void go() {
      System.out.println("Jadę w dół.");
    }
  };

  public abstract void go();

  @Override
  public String toString() {
    return this.name;
  }

  private Direction(String name) {
    this.name = name;
  }

  private final String name;
}
