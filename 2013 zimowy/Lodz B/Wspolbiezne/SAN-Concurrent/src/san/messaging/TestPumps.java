package san.messaging;

public class TestPumps {

  public static void main(String... args) {
    IPump pump = Pump.createAsync();

    Object sender = 123;
    Object value = 345;

    Target receiver = new Target() {
      @Override
      public void onMessage(Object message, Object source) {
        System.out.println("Dostałem " + message + " od " + source);
      }
    };

    pump.register(receiver, Integer.class);
    
    pump.send(value, sender);

  }

}
