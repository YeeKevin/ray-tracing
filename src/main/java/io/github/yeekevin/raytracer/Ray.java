package io.github.yeekevin.raytracer;

public record Ray(Vec3 origin, Vec3 direction) {

    // P(t) = A + t*b
    public Vec3 at(double t) {
        return origin.plus(direction.times(t));
    }
}
