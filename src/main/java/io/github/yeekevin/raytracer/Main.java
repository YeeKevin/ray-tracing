package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    static double hitSphere(Vec3 center, double radius, Ray r) {
        Vec3 oc = center.minus(r.origin());
        double a = r.direction().dot(r.direction());
        double b = -2.0 * r.direction().dot(oc);
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-b - Math.sqrt(discriminant)) / (2.0 * a);
        }
    }

    static Vec3 rayColor(Ray r) {
        double t = hitSphere(new Vec3(0, 0, -1), 0.5, r);
        if (t > 0.0) {
            Vec3 n = r.at(t).minus(new Vec3(0, 0, -1)).unitVector();
            return new Vec3(n.x() + 1, n.y() + 1, n.z() + 1).times(0.5);
        }

        Vec3 unitDirection = r.direction().unitVector();
        double a = 0.5 * (unitDirection.y() + 1.0);
        return new Vec3(1.0, 1.0, 1.0).times(1.0 - a)
                .plus(new Vec3(0.5, 0.7, 1.0).times(a));
    }

    public static void main(String[] args) throws IOException {
        // Image
        double aspectRatio = 16.0 / 9.0;
        int imageWidth = 400;

        // Calculate the image height and ensure that it's at least 1
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);

        // Camera
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);
        Vec3 cameraCenter = new Vec3(0, 0, 0);

        // Calculate the vectors across the horizontal and down the vertical viewport edges
        Vec3 viewportU = new Vec3(viewportWidth, 0, 0);
        Vec3 viewportV = new Vec3(0, -viewportHeight, 0);

        // Calculate the horizontal and vertical delta vectors from pixel to pixel
        Vec3 pixelDeltaU = viewportU.div(imageWidth);
        Vec3 pixelDeltaV = viewportV.div(imageHeight);

        // Calculate the location of the upper left pixel
        Vec3 viewportUpperLeft = cameraCenter
                .minus(new Vec3(0, 0, focalLength))
                .minus(viewportU.div(2))
                .minus(viewportV.div(2));
        Vec3 pixel00Loc = viewportUpperLeft.plus(pixelDeltaU.plus(pixelDeltaV).times(0.5));


        // Render
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("image.ppm")))) {
            out.print("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = 0; j < imageHeight; j++) {
                System.err.print("\rScanlines remaining: " + (imageHeight - j) + " ");
                System.err.flush();

                for (int i = 0; i < imageWidth; i++) {
                    Vec3 pixelCenter = pixel00Loc
                            .plus(pixelDeltaU.times(i))
                            .plus(pixelDeltaV.times(j));
                    Vec3 rayDirection = pixelCenter.minus(cameraCenter);
                    Ray r = new Ray(cameraCenter, rayDirection);

                    Vec3 pixelColor = rayColor(r);
                    Color.writeColor(out, pixelColor);
                }
            }

            System.err.print("\rDone.                 \n");
        }
    }
}