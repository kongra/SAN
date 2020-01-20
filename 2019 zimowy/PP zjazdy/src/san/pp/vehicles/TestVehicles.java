package san.pp.vehicles;

public class TestVehicles {

  public static void travel(Vehicle v) {
    v.start();
    System.out.println("Traveling...");
    v.stop();
  }

  public static void main(String... args) {
    Bike b1 = new Bike();
    travel(b1);

    Tesla t2 = new Tesla();
    travel(t2);

  }

}
