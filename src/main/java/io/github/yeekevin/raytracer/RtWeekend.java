package io.github.yeekevin.raytracer;

public final class RtWeekend {

    private RtWeekend() {} // utility class

    // Constants
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    public static final double PI = Math.PI;

    // Utility Functions
    public static double degreesToRadians(double degrees) {
        return degrees * PI / 180.0;
    }
}
