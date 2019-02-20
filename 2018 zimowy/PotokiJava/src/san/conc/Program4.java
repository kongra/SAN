package san.conc;

public class Program4 {

  public static void main(String... args) {
    Unary<Long, Boolean> even = (n) ->  {
      try {
        Thread.sleep(2000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return n % 2 == 0;
    };

    Memo<Long, Boolean> meven = new Memo<>(even);

    System.out.println(meven.call(55L));
    System.out.println(meven.call(55L));
    System.out.println(meven.call(55L));
    System.out.println(meven.call(55L));

  }

}
