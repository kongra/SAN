// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface Program1 {

  record Point(double x, double y) {}

  record Line(Point p1, Point p2) {}

  static String repr(Object obj) {
    return switch (obj) {
      case Point(var x, var y)  -> "(%s, %s)".formatted(x, y);
      case Line(var p1, var p2) -> "[%s, %s]".formatted(repr(p1), repr(p2));
      default                   -> "Unknown";
    };
  }

  static String interpolationUsingSTRProcessor(String feelsLike,
      double temperature, String unit) {
    return STR."Today's weather is \{ feelsLike }, with a temperature of \{ temperature + 2 } degrees \{ unit }";
  }

  public static void main(String... args) throws InterruptedException {
    var thread = Thread.ofVirtual().unstarted(() -> {
      Line line = new Line(new Point(1, 2), new Point(3, 4));
      System.out.println(repr(line));
      System.out.println("Running in " + Thread.currentThread().toString());
      System.out.println(interpolationUsingSTRProcessor("cold", 4.5, "Celsius"));
    });

    thread.start();
    thread.join();
  }

}
