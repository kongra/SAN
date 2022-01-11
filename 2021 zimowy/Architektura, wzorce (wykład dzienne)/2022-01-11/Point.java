class Point implements Sendable {

  private double x;

  private double y;

  Point (double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void send() {
    System.out.println("Point::send " + x +
                       " y " + y);
  }

}
