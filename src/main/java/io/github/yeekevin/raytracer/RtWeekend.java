package io.github.yeekevin.raytracer;

import java.util.concurrent.ThreadLocalRandom;

public final class RtWeekend {

    private RtWeekend() {} // utility class

    // Constants
    public static final double INFINITY = Double.POSITIVE_INFINITY;
    public static final double PI = Math.PI;

    // Utility Functions
    public static double degreesToRadians(double degrees) {
        return degrees * PI / 180.0;
    }

    // Returns a random real in [0,1).
    public static double randomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    // Returns a random real in [min, max).
    public static double randomDouble(double min, double max) {
        return min + (max - min) * randomDouble();
    }
}
