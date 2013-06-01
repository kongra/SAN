package test.san.jipp.util;

import san.jipp.util.Direction;

public class TestEnums {

  public static void main(String[] args) {
    Direction d = Direction.LEFT;
    System.out.println(d);
    d.turn();
  }
  
}
