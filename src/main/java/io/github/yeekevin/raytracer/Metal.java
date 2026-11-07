package io.github.yeekevin.raytracer;

import java.util.Optional;

public class Metal implements Material {
    private final Vec3 albedo;
    private final double fuzz;

    public Metal(Vec3 albedo, double fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz < 1 ? fuzz : 1;
    }

    @Override
    public Optional<ScatterResult> scatter(Ray rIn, HitRecord rec) {
        Vec3 reflected = Vec3.reflect(rIn.direction(), rec.normal);
        reflected = reflected.unitVector().plus(Vec3.randomUnitVector().times(fuzz));
        Ray scattered = new Ray(rec.p, reflected);
        if (scattered.direction().dot(rec.normal) > 0) {
            return Optional.of(new ScatterResult(albedo, scattered));
        }
        return Optional.empty();
    }
}
