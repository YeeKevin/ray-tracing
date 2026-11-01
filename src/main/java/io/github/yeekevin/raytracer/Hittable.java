package io.github.yeekevin.raytracer;

public interface Hittable {
    boolean hit(Ray r, Interval rayT, HitRecord rec);
}
