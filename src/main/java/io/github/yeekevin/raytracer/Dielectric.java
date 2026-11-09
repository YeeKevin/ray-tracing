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
        double cosTheta = Math.min(unitDirection.negate().dot(rec.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - cosTheta * cosTheta);

        boolean cannotRefract = ri * sinTheta > 1.0;
        Vec3 direction;

        if (cannotRefract || reflectance(cosTheta, ri) > RtWeekend.randomDouble()) {
            // Must Reflect
            direction = Vec3.reflect(unitDirection, rec.normal);
        } else {
            // Can Refract
            direction = Vec3.refract(unitDirection, rec.normal, ri);
        }

        Ray scattered = new Ray(rec.p, direction);
        return Optional.of(new ScatterResult(attenuation, scattered));
    }

    private static double reflectance(double cosine, double refractionIndex) {
        // Use Schlick's approximation for reflectance.
        double r0 = (1 - refractionIndex) / (1 + refractionIndex);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }
}
