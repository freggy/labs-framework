package de.bergwerklabs.framework.commons.math.vector;

public class Vector3D {

  private double x, y, z;

  public Vector3D() {
    this(0.0, 0.0, 0.0);
  }

  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double dotProduct(Vector3D other) {
    return x * other.x + y * other.y + z * other.z;
  }

  public double length() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  public Vector3D normalized() {
    double length = this.length();
    return new Vector3D(x / length, y / length, z / length);
  }

  public Vector3D set(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  public Vector3D add(Vector3D other) {
    return new Vector3D(x + other.x, y + other.y, z + other.z);
  }

  public Vector3D add(double value) {
    return add(new Vector3D(value, value, value));
  }

  public Vector3D subtract(Vector3D other) {
    return new Vector3D(x - other.x, y - other.y, z - other.z);
  }

  public Vector3D subtract(double value) {
    return subtract(new Vector3D(value, value, value));
  }

  public Vector3D multiply(Vector3D other) {
    return new Vector3D(x * other.x, y * other.y, z * other.z);
  }

  public Vector3D multiply(double value) {
    return multiply(new Vector3D(value, value, value));
  }

  public Vector3D divide(Vector3D other) {
    return new Vector3D(x / other.x, y / other.y, z / other.z);
  }

  public Vector3D divide(double value) {
    return divide(new Vector3D(value, value, value));
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public Vector3F toFloatVector() {
    return new Vector3F((float) x, (float) y, (float) z);
  }

  @Override
  public String toString() {
    return "Vector3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
  }
}
