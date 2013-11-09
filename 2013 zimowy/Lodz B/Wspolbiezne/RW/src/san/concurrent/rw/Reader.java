package san.concurrent.rw;

public class Reader implements Runnable {

  private final Book book;
  
  private final long timeout;

  public Reader(Book book, long timeout) {
    this.book = book;
    this.timeout = timeout;
  }

  @Override
  public void run() {
    while(true) {
      try {
        Thread.sleep(timeout);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
      
      book.read(this);
    }
  }
}
