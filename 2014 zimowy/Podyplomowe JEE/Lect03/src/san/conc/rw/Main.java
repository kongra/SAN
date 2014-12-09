package san.conc.rw;

public class Main {

  public static void main(String[] args) {
    Board b = new Board();
    for (int i = 0; i < 6; i++) {
      new Thread(new Reader(String.valueOf(i + 1), b)).start();
    }
    for (int i = 0; i < 3; i++) {
      new Thread(new Writer(String.valueOf(i + 1), b)).start();
    }
  }

}
