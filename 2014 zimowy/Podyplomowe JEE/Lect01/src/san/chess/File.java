package san.chess;

public enum File {

  A("a"), B("b"), C("c"), D("d"), E("e"), F("f"), G("g"), H("h");

  private final String repr;

  private File(String repr) {
    this.repr = repr;
  }

  @Override
  public String toString() {
    return repr;
  }

}
