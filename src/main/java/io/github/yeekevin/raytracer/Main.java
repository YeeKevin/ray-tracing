package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HittableList world = new HittableList();
        world.add(new Sphere(new Vec3(0, 0, -1), 0.5));
        world.add(new Sphere(new Vec3(0, -100.5, -1), 100));

        Camera cam = new Camera();
        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 400;

        cam.render(world);
    }
}
