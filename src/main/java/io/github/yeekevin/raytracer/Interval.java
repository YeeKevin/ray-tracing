package io.github.yeekevin.raytracer;

public record Interval(double min, double max) {

    public static final Interval EMPTY =
            new Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    public static final Interval UNIVERSE =
            new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

    // Default interval is empty
    public Interval() {
        this(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    public double size() {
        return max - min;
    }

    public boolean contains(double x) {
        return min <= x && x <= max;
    }

    public boolean surrounds(double x) {
        return min < x && x < max;
    }

    public double clamp(double x) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}
