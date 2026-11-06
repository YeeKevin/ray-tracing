package io.github.yeekevin.raytracer;

public class HitRecord {
    public Vec3 p;
    public Vec3 normal;
    public Material mat;
    public double t;
    public boolean frontFace;

    // Sets the hit record normal vector
    // Note: the parameter outwardNormal is assumed to have unit length
    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = r.direction().dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.negate();
    }

    public void copyFrom(HitRecord other) {
        this.p = other.p;
        this.normal = other.normal;
        this.mat = other.mat;
        this.t = other.t;
        this.frontFace = other.frontFace;
    }
}
