class Point {

  final double x; // const

  final double y; // const

  Point(/* Point this, */ double initX, double initY) {
    this.x = initX;
    this.y = initY;
  }

  Point add(/* Point this, */ Point p2) {
    return new Point(this.x + p2.x, this.y + p2.y);
  }

  String asString(/* Point this, */) {
    return "(" + this.x + ", " + this.y + ")";
  }

}

// this
