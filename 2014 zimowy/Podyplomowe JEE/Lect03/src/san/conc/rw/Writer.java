package san.conc.rw;

public class Writer implements Runnable {

  private final String name;

  private final Board board;

  public Writer(String name, Board board) {
    this.name = name;
    this.board = board;
  }

  @Override
  public void run() {
    for (;;) {
      System.out.println("Writer " + name + " wants to write.");
      board.write();
      System.out.println("Writer " + name + " goes to sleep.");
      Time.sleepAtMost(3000);
    }
  }

}
