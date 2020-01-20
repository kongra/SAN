package san.pp.vehicles;

public abstract class Car implements Vehicle {

  @Override
  public void start() {
    startEngineIfNotRunning();
    increaseRPM();
    releaseBreakes();
  }

  protected abstract void increaseRPM();

  protected abstract void startEngineIfNotRunning();

  protected abstract void releaseBreakes();

  @Override
  public void stop() {
    stepOnTheBreakes();
    decreaseRPM();
  }

  protected abstract void decreaseRPM();

  protected abstract void stepOnTheBreakes();
}
