package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HittableList world = new HittableList();

        Material materialGround = new Lambertian(new Vec3(0.8, 0.8, 0.0));
        Material materialCenter = new Lambertian(new Vec3(0.1, 0.2, 0.5));
        Material materialLeft = new Metal(new Vec3(0.8, 0.8, 0.8));
        Material materialRight = new Metal(new Vec3(0.8, 0.6, 0.2));

        world.add(new Sphere(new Vec3( 0.0,-100.5,  -1.0), 100.0, materialGround));
        world.add(new Sphere(new Vec3( 0.0,   0.0,  -1.2),   0.5, materialCenter));
        world.add(new Sphere(new Vec3(-1.0,   0.0,  -1.0),   0.5, materialLeft));
        world.add(new Sphere(new Vec3( 1.0,   0.0,  -1.0),   0.5, materialRight));

        Camera cam = new Camera();
        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 400;
        cam.samplesPerPixel = 100;
        cam.maxDepth = 50;

        cam.render(world);
    }
}
