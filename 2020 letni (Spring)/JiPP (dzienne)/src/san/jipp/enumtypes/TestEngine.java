package san.jipp.enumtypes;

public class TestEngine {

  public static void main(String... args) {
    Engine e1 = new Engine();
    System.out.println("Obecna emisja wynosi " + e1.currentEmission());

    e1.shiftUpState().shiftUpState().shiftUpState().shiftUpState();
    System.out.println("Obecna emisja wynosi " + e1.currentEmission());
  }

}
