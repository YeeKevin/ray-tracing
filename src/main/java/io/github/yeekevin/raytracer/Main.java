package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HittableList world = new HittableList();

        double R = Math.cos(RtWeekend.PI / 4);

        Material materialLeft = new Lambertian(new Vec3(0, 0, 1));
        Material materialRight = new Lambertian(new Vec3(1, 0, 0));

        world.add(new Sphere(new Vec3(-R, 0, -1), R, materialLeft));
        world.add(new Sphere(new Vec3( R, 0, -1), R, materialRight));

        Camera cam = new Camera();
        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 400;
        cam.samplesPerPixel = 100;
        cam.maxDepth = 50;

        cam.vfov = 90;

        cam.render(world);
    }
}
