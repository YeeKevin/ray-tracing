package io.github.yeekevin.raytracer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Camera {
    public double aspectRatio = 1.0;  // Ratio of image width over height
    public int imageWidth = 100;      // Rendered image width in pixel count
    public int samplesPerPixel = 10;  // Count of random samples for each pixel
    public int maxDepth = 10;         // Maximum number of ray bounces into scene

    public double vfov = 90;                    // Vertical view angle (field of view)
    public Vec3 lookfrom = new Vec3(0, 0, 0);   // Point camera is looking from
    public Vec3 lookat = new Vec3(0, 0, -1);    // Point camera is looking at
    public Vec3 vup = new Vec3(0, 1, 0);        // Camera-relative "up" direction

    private int imageHeight;          // Rendered image height
    private Vec3 center;              // Camera center
    private Vec3 pixel00Loc;          // Location of pixel 0, 0
    private Vec3 pixelDeltaU;         // Offset to pixel to the right
    private Vec3 pixelDeltaV;         // Offset to pixel below
    private double pixelSamplesScale; // Color scale factor for a sum of pixel samples
    private Vec3 u, v, w;             // Camera frame basis vectors

    public void render(Hittable world) throws IOException {
        initialize();

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("image.ppm")))) {
            out.print("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int j = 0; j < imageHeight; j++) {
                System.err.print("\rScanlines remaining: " + (imageHeight - j) + " ");
                System.err.flush();

                for (int i = 0; i < imageWidth; i++) {
                    Vec3 pixelColor = new Vec3(0, 0, 0);
                    for (int sample = 0; sample < samplesPerPixel; sample++) {
                        Ray r = getRay(i , j);
                        pixelColor = pixelColor.plus(rayColor(r, maxDepth, world));
                    }
                    Color.writeColor(out, pixelColor.times(pixelSamplesScale));
                }
            }

            System.err.print("\rDone.                 \n");
        }
    }

    private Ray getRay(int i, int j) {
        // Construct a camera ray originating form the origin and directed at a randomly
        // sampled point around the pixel lcoation i, j.
        Vec3 offset = sampleSquare();
        Vec3 pixelSample = pixel00Loc
                .plus(pixelDeltaU.times(i + offset.x()))
                .plus(pixelDeltaV.times(j + offset.y()));

        Vec3 rayOrigin = center;
        Vec3 rayDirection = pixelSample.minus(rayOrigin);
        return new Ray(rayOrigin, rayDirection);
    }

    private Vec3 sampleSquare() {
        // Returns the vector to a random point in the [-0.5, -.5]-[+.5,+.5] unit square
        return new Vec3(RtWeekend.randomDouble() - 0.5, RtWeekend.randomDouble() - 0.5, 0);
    }

    private void initialize() {
        imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = Math.max(imageHeight, 1);
        pixelSamplesScale = 1.0 / samplesPerPixel;

        center = lookfrom;

        // Determine viewport dimensions.
        double focalLength = lookfrom.minus(lookat).length();
        double theta = RtWeekend.degreesToRadians(vfov);
        double h = Math.tan(theta / 2);
        double viewportHeight = 2 * h * focalLength;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);

        // Calculate the u,v,w unit basis vectors for the camera coordinate frame.
        w = lookfrom.minus(lookat).unitVector();
        u = vup.cross(w).unitVector();
        v = w.cross(u);

        // Calculate the vectors across the horizontal and down the vertical viewport edges.
        Vec3 viewportU = u.times(viewportWidth);            // Vector across viewport horizontal edge
        Vec3 viewportV = v.negate().times(viewportHeight);  // Vector down viewport vertical edge

        // Calculate the horizontal and vertical delta vectors from pixel to pixel.
        pixelDeltaU = viewportU.div(imageWidth);
        pixelDeltaV = viewportV.div(imageHeight);

        // Calculate the location of the upper left pixel.
        Vec3 viewportUpperLeft = center
                .minus(w.times(focalLength))
                .minus(viewportU.div(2))
                .minus(viewportV.div(2));
        pixel00Loc = viewportUpperLeft.plus(pixelDeltaU.plus(pixelDeltaV).times(0.5));
    }

    private Vec3 rayColor(Ray r, int depth, Hittable world) {
        // If we've exceeded the ray bounce limit, no more light is gathered
        if (depth <= 0) {
            return new Vec3(0, 0, 0);
        }

        HitRecord rec = new HitRecord();
        if (world.hit(r, new Interval(0.001, RtWeekend.INFINITY), rec)) {
            return rec.mat.scatter(r, rec)
                    .map(s -> s.attenuation().times(rayColor(s.scattered(), depth - 1, world)))
                    .orElse(new Vec3(0, 0, 0));
        }

        Vec3 unitDirection = r.direction().unitVector();
        double a = 0.5 * (unitDirection.y() + 1.0);
        return new Vec3(1.0, 1.0, 1.0).times(1.0 - a)
                .plus(new Vec3(0.5, 0.7, 1.0).times(a));
    }
}
