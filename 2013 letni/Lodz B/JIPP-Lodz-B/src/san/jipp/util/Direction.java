package san.jipp.util;

public enum Direction {

  RIGHT("prawo") {
    @Override
    public void turn() {
      System.out.println("Skręcam w prawo");
    }
  },

  LEFT("lewo") {
    @Override
    public void turn() {
      System.out.println("Skręcam w lewo");
    }
  },

  UP("góra") {
    @Override
    public void turn() {
      System.out.println("Jadę w górę");
    }
  },

  DOWN("dół") {
    @Override
    public void turn() {
      System.out.println("Jadę w dół");
    }
  };

  private final String name;

  private Direction(String name) {
    this.name = name;
  }

  public abstract void turn();

}
