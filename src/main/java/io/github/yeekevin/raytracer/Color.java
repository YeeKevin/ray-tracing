package io.github.yeekevin.raytracer;

import java.io.PrintWriter;

public final class Color {

    private static final Interval INTENSITY = new Interval(0.000, 0.999);

    private Color() {} // utility class, nto meant to be instantiated

    public static void writeColor(PrintWriter out, Vec3 pixelColor) {
        double r = pixelColor.x();
        double g = pixelColor.y();
        double b = pixelColor.z();

        // Translate the [0,1] component values to the byte range [0,255]
        int rbyte = (int) (256 * INTENSITY.clamp(r));
        int gbyte = (int) (256 * INTENSITY.clamp(g));
        int bbyte = (int) (256 * INTENSITY.clamp(b));

        // Write out the pixel color components
        out.print(rbyte + " " + gbyte + " " + bbyte + "\n");
    }
}
