package san.pp.vehicles;

public class Bike implements Vehicle {

  @Override
  public void start() {
    System.out.println(
        "Zaczynam pedałować.");
  }

  @Override
  public void stop() {
    System.out.println(
        "Przestaję pedałować i wciskam" +
            "hamulec");
  }
}
