package io.github.yeekevin.raytracer;

public interface Hittable {
    boolean hit(Ray r, double rayTmin, double rayTmax, HitRecord rec);
}
