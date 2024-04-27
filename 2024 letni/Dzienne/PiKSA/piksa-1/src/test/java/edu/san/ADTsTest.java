// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Objects;
import java.util.function.IntSupplier;

class ADTsTest {

  static void runOnStatus(int status) {
    switch (status) {
      case 0:
        System.out.println("Turn the engine down");
        break;
      case 3:
        System.out.println("Turn the engine up");
        break;
      case 4:
        System.out.println("Speed up");
        break;
      default:
        System.out.println("Do nothing");
        break;
    }
  }

  enum EngineAction {
    TURN_ON, TURN_DOWN, SPEED_UP, DO_NOTHING, SLOW_DOWN;
  }

  static int toInt(EngineAction engineAction) {
    return switch (engineAction) {
      case DO_NOTHING -> Integer.MAX_VALUE;
      case SPEED_UP   -> 4;
      case TURN_DOWN  -> 0;
      case TURN_ON    -> 3;
      case SLOW_DOWN  -> 7;
    };
  }

//  static interface EngineEvent {
//    int toInt();
//  }
//
//  record DoNothing() implements EngineEvent {
//    @Override
//    public int toInt() {
//      return Integer.MAX_VALUE;
//    }
//  }

  static class EngineEvent {

    private final IntSupplier statusSupplier;

    EngineEvent(IntSupplier statusSupplier) {
      Objects.requireNonNull(statusSupplier);
      this.statusSupplier = statusSupplier;
    }

    int toInt() {
      return statusSupplier.getAsInt();
    }
  }

  class DoNothing extends EngineEvent {
    DoNothing() {
      super(() -> Integer.MAX_VALUE);
    }
  }

}
