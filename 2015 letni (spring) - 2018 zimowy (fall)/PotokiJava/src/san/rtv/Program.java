package san.rtv;

public class Program {

  private final int value;

  public Program(int value) {
    if (value < 1 || value > 1000) {
      throw new IllegalArgumentException("Program out of range " + value);
    }
    this.value = value;
  }

  public int value() {
    return this.value;
  }
}
