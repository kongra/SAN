package san.concurrent.rw;

public class Writer implements Runnable {

  private final Book book;
  
  private final long timeout;

  public Writer(Book book, long timeout) {
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
      
      book.write(this);
    }
  }
}
