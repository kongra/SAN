package edu.san.factories;

public class Program3 {

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

  static interface InstrumentsFactory {

    Instrument createInstrument();

  }

  static class PianoFactory implements InstrumentsFactory {
    @Override
    public Instrument createInstrument() {
      return new Piano();
    }
  }

  static class ViolinFactory implements InstrumentsFactory {
    @Override
    public Instrument createInstrument() {
      return new Violin();
    }
  }

  static void foo(InstrumentsFactory factory) {
    var instr = factory.createInstrument();
    instr.play();
  }

  public static void main(String... args) {
    foo(new PianoFactory());
    foo(new ViolinFactory());
  }

}
