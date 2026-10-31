package io.github.yeekevin.raytracer;

public record Vec3(double x, double y, double z) {

    public Vec3() {
        this(0, 0, 0);
    }

    public double get(int i) {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> throw new IndexOutOfBoundsException("Vec3 index: " + i);
        };
    }

    public Vec3 negate() {
        return new Vec3(-x, -y, -z);
    }

    public Vec3 plus(Vec3 v) {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    public Vec3 minus(Vec3 v) {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    // Component-wise multiply (used later for colors)
    public Vec3 times(Vec3 v) {
        return new Vec3(x * v.x, y * v.y, z * v.z);
    }

    public Vec3 times(double t) {
        return new Vec3(x * t, y * t, z * t);
    }

    public Vec3 div(double t) {
        return times(1 / t);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(y * v.z - z * v.y,
                        z * v.x - x * v.z,
                        x * v.y - y * v.x);
    }

    public Vec3 unitVector() {
        return div(length());
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}
