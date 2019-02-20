package san.rtv;

public class Test {

  public static void main(String... args) {
    SonyBravia sonyBravia = new SonyBravia();

    TV set = sonyBravia;
    set.setProgram(new Program(2));
    set.changeBrightness(75);
    set.changeVolume(30);

    Phone ph = sonyBravia;
    ph.call(111111111);

  }

}
