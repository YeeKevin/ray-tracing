package io.github.yeekevin.raytracer;

import java.util.Optional;

public class Metal implements Material {
    private final Vec3 albedo;

    public Metal(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public Optional<ScatterResult> scatter(Ray rIn, HitRecord rec) {
        Vec3 reflected = Vec3.reflect(rIn.direction(), rec.normal);
        Ray scattered = new Ray(rec.p, reflected);
        return Optional.of(new ScatterResult(albedo, scattered));
    }
}
