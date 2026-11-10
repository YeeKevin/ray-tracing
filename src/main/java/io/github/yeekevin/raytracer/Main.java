package io.github.yeekevin.raytracer;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HittableList world = new HittableList();

        Material groundMaterial = new Lambertian(new Vec3(0.5, 0.5, 0.5));
        world.add(new Sphere(new Vec3(0, -1000, 0), 1000, groundMaterial));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double chooseMat = RtWeekend.randomDouble();
                Vec3 center = new Vec3(a + 0.9 * RtWeekend.randomDouble(), 0.2, b + 0.9 * RtWeekend.randomDouble());

                if (center.minus(new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphereMaterial;

                    if (chooseMat < 0.8) {
                        // diffuse
                        Vec3 albedo = Vec3.random().times(Vec3.random());
                        sphereMaterial = new Lambertian(albedo);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else if (chooseMat < 0.95) {
                        // metal
                        Vec3 albedo = Vec3.random(0.5, 1);
                        double fuzz = RtWeekend.randomDouble(0, 0.5);
                        sphereMaterial = new Metal(albedo, fuzz);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    } else {
                        // glass
                        sphereMaterial = new Dielectric(1.5);
                        world.add(new Sphere(center, 0.2, sphereMaterial));
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5);
        world.add(new Sphere(new Vec3(0, 1, 0), 1.0, material1));

        Material material2 = new Lambertian(new Vec3(0.4, 0.2, 0.1));
        world.add(new Sphere(new Vec3(-4, 1, 0), 1.0, material2));

        Material material3 = new Metal(new Vec3(0.7, 0.6, 0.5), 0.0);
        world.add(new Sphere(new Vec3(4, 1, 0), 1.0, material3));

        Camera cam = new Camera();
        cam.aspectRatio = 16.0 / 9.0;
        cam.imageWidth = 1200;
        cam.samplesPerPixel = 500;
        cam.maxDepth = 50;

        cam.vfov = 20;
        cam.lookfrom = new Vec3(13, 2, 3);
        cam.lookat = new Vec3(0, 0, 0);
        cam.vup = new Vec3(0, 1, 0);

        cam.defocusAngle = 0.6;
        cam.focusDist = 10.0;

        cam.render(world);
    }
}
