package san.rtv;

public class SonyBravia implements TV, Phone {

  private Program p = new Program(1);

  private int volume = 0;

  private int brightness = 50;

  @Override
  public void setProgram(Program p) {
    this.p = p;
  }

  @Override
  public void changeVolume(int delta) {
    int volume1 = this.volume + delta;
    if (volume1 < 0) this.volume = 0;
    else if (volume1 > 100) this.volume = 100;
    else this.volume = volume1;
  }

  @Override
  public void changeBrightness(int delta) {
    // TODO:
  }

  @Override
  public void call(int number) {
    // TODO:
  }
}
