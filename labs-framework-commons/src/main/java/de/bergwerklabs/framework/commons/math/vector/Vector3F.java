package de.bergwerklabs.framework.commons.math.vector;

public class Vector3F {

    private float x, y, z;

    public Vector3F() {
        this(0.0f, 0.0f, 0.0f);
    }

    public Vector3F(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float dotProduct(Vector3F other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3F normalized() {
        float length = this.length();
        return new Vector3F(x / length, y / length, z / length);
    }

    public Vector3F set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3F add(Vector3F other) {
        return new Vector3F(x + other.x, y + other.y, z + other.z);
    }

    public Vector3F add(float value) {
        return add(new Vector3F(value, value, value));
    }

    public Vector3F subtract(Vector3F other) {
        return new Vector3F(x - other.x, y - other.y, z - other.z);
    }

    public Vector3F subtract(float value) {
        return subtract(new Vector3F(value, value, value));
    }

    public Vector3F multiply(Vector3F other) {
        return new Vector3F(x * other.x, y * other.y, z * other.z);
    }

    public Vector3F multiply(float value) {
        return multiply(new Vector3F(value, value, value));
    }

    public Vector3F divide(Vector3F other) {
        return new Vector3F(x / other.x, y / other.y, z / other.z);
    }

    public Vector3F divide(float value) {
        return divide(new Vector3F(value, value, value));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3D toDoubleVector() {
        return new Vector3D(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3F{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
