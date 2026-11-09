package io.github.yeekevin.raytracer;

import java.util.Optional;

public class Dielectric implements Material {
    // Refractive index in vacuum or air, or the ratio of the material's refractive index over
    // the refractive index of the enclosing media
    private final double refractionIndex;

    public Dielectric(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public Optional<ScatterResult> scatter(Ray rIn, HitRecord rec) {
        Vec3 attenuation = new Vec3(1.0, 1.0, 1.0);
        double ri = rec.frontFace ? (1.0 / refractionIndex) : refractionIndex;

        Vec3 unitDirection = rIn.direction().unitVector();
        Vec3 refracted = Vec3.refract(unitDirection, rec.normal, ri);

        Ray scattered = new Ray(rec.p, refracted);
        return Optional.of(new ScatterResult(attenuation, scattered));
    }
}
