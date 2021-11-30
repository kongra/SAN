package edu.san.factories;

public class Program2 {

  static interface Instrument {
    void play();
  }

  static class Piano implements Instrument {
    @Override
    public void play() {
      System.out.println("Piano plays...");
    }
  }

  static class DoubleBass implements Instrument {
    @Override
    public void play() {
      System.out.println("DoubleBass plays...");
    }
  }

  static class Violin implements Instrument {
    @Override
    public void play() {
      System.out.println("Violin plays...");
    }
  }

  static class InstrumentsFactory {

    public Instrument createInstrument(String instrumentName) {
      if ("Piano".equalsIgnoreCase(instrumentName)) {
        return new Piano();
      } else if ("DoubleBass".equalsIgnoreCase(instrumentName)) {
        return new DoubleBass();
      } else {
        throw new UnsupportedOperationException(
            "Insturment not found " + instrumentName);
      }
    }

  }

  public static void main(String... args) {
    var factory = new InstrumentsFactory();
    var i1 = factory.createInstrument("piano");

    i1.play();
  }

}
