package io.github.yeekevin.raytracer;

import java.io.PrintWriter;

public final class Color {

    private Color() {} // utility clas, nto meant to be instantiated

    public static void writeColor(PrintWriter out, Vec3 pixelColor) {
        double r = pixelColor.x();
        double g = pixelColor.y();
        double b = pixelColor.z();

        // Translate the [0,1] component values to the byte range [0,255]
        int rbyte = (int) (255.999 * r);
        int gbyte = (int) (255.999 * g);
        int bbyte = (int) (255.999 * b);

        // Write out the pixel color components
        out.print(rbyte + " " + gbyte + " " + bbyte + "\n");
    }
}
