package san.conc.rw;

public class Reader implements Runnable {

  private final String name;

  private final Board board;

  public Reader(String name, Board board) {
    this.name = name;
    this.board = board;
  }

  @Override
  public void run() {
    for (;;) {
      System.out.println("Reader " + name + " wants to read.");
      board.read();
      System.out.println("Reader " + name + " goes to sleep.");
      Time.sleepAtMost(2000);
    }
  }

}
