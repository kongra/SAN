package nonstattionary.leck20181118;

class Vector3D {

  double x;

  double y;

  double z;

  Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  double length() {
    return Math.sqrt(
        this.x * this.x +
        this.y * this.y +
            this.z * this.z);
  }

}
