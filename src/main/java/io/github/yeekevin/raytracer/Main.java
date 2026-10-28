package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Image
        int imageWidth = 256;
        int imageHeight = 256;

        // Render
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("image.ppm")))) {
            out.print("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = 0; j < imageHeight; j++) {
                System.err.print("\rScanlines remaining: " + (imageHeight - j) + " ");
                System.err.flush();

                for (int i = 0; i < imageWidth; i++) {
                    Vec3 pixelColor = new Vec3(
                            (double) i / (imageWidth - 1),
                            (double) j / (imageHeight - 1),
                            0);
                    Color.writeColor(out, pixelColor);
                }
            }

            System.err.print("\rDone.                 \n");
        }
    }
}