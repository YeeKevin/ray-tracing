package io.github.yeekevin.raytracer;

public record ScatterResult(Vec3 attenuation, Ray scattered) {
}
