package san.jipp.enumtypes;

public class Engine {

  private EngineState state = EngineState.OFF;

  // private EngineState1 state1 = EngineState1.OFF;

  public double currentEmission() {
    return state.currentEmission();
  }

  public Engine shiftUpState() {
    var next = state.next();
    if (next != null) {
      state = next;
    }
    return this;
  }

}
