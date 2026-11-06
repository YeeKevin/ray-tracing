package io.github.yeekevin.raytracer;

import java.util.Optional;

public class Lambertian implements Material {
    private final Vec3 albedo;


    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public Optional<ScatterResult> scatter(Ray rIn, HitRecord rec) {
        Vec3 scatterDirection = rec.normal.plus(Vec3.randomUnitVector());

        // Catch degenerate scatter direction
        if (scatterDirection.nearZero()) {
            scatterDirection = rec.normal;
        }

        Ray scattered = new Ray(rec.p, scatterDirection);
        return Optional.of(new ScatterResult(albedo, scattered));
    }
}
