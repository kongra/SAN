package san.jee.lect03;

public class TestEnums {

  public static void main(String[] args) {
    @SuppressWarnings("unused")
    FeedEntry entry = FeedEntry.HIGH;
    
    System.out.println(FeedEntry.LOW.throughput());
  }

}
