class Point {

  double x;

  double y;

  Point(double initX, double initY) {
    x = initX;
    y = initY;
  }

  Point add(Point p2) {
    return new Point(x + p2.x, y + p2.y);
  }

  String asString() {
    return "(" + x + ", " + y + ")";
  }

  // static Point make(double x, double y) {
  //   // Point p = new Point();
  //   // p.x = x;
  //   // p.y = y;
  //   // return p;

  //   // Point p = new Point(x, y);
  //   // return p;

  //   return new Point(x, y);
  // }

  // static Point add(Point p1, Point p2) {
  //   return make(p1.x + p2.x, p1.y + p2.y);
  // }

  // static String asString(Point p) {
  //   return "(" + p.x + ", " + p.y + ")";
  // }

}
