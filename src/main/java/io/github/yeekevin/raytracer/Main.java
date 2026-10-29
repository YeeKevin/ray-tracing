package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    static Vec3 rayColor(Ray r) {
        return new Vec3(0, 0, 0);
    }

    public static void main(String[] args) throws IOException {
        // Image
        double aspectRatio = 16.0 / 9.0;
        int imageWidth = 400;

        // Calculate the image height and ensure that it's at least 1
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);

        // Viewport widths less than one are pok since they are real valued
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);

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