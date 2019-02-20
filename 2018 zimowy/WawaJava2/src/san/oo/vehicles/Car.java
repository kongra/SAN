package san.oo.vehicles;

public class Car {

  private boolean running;

  public Car() {
    running = true;
  }

  public void run() {
    this.running = true;
  }

  public void stop() {
    this.running = false;
  }

}
