package io.github.yeekevin.raytracer;

import java.util.Optional;

public interface Material {
    // Returns empty if the ray is absorbed instead of scattered
    default Optional<ScatterResult> scatter(Ray rIn, HitRecord rec) {
        return Optional.empty();
    }
}
