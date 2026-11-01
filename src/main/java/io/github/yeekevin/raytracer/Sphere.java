package io.github.yeekevin.raytracer;

public class Sphere implements Hittable {
    private final Vec3 center;
    private final double radius;

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = Math.max(0, radius);
    }

    @Override
    public boolean hit(Ray r, Interval rayT, HitRecord rec) {
        Vec3 oc = center.minus(r.origin());
        double a = r.direction().lengthSquared();
        double h = r.direction().dot(oc);
        double c = oc.lengthSquared() - radius * radius;

        double discriminant = h * h - a * c;
        if (discriminant < 0) {
            return false;
        }

        double sqrtd = Math.sqrt(discriminant);

        // Find the nearest root that lies in the acceptable range
        double root = (h - sqrtd) / a;
        if (!rayT.surrounds(root)) {
            root = (h + sqrtd) / a;
            if (!rayT.surrounds(root)) {
                return false;
            }
        }

        rec.t = root;
        rec.p = r.at(rec.t);
        Vec3 outwardNormal = rec.p.minus(center).div(radius);
        rec.setFaceNormal(r, outwardNormal);
        return true;
    }
}
