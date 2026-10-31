package io.github.yeekevin.raytracer;

import java.util.ArrayList;
import java.util.List;

public class HittableList implements Hittable {
    public final List<Hittable> objects = new ArrayList<>();

    public HittableList() {}

    public HittableList(Hittable object) {
        add(object);
    }

    public void clear() {
        objects.clear();
    }

    public void add(Hittable object) {
        objects.add(object);
    }

    @Override
    public boolean hit(Ray r, double rayTmin, double rayTmax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = rayTmax;

        for (Hittable object : objects) {
            if (object.hit(r, rayTmin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec.copyFrom(tempRec);
            }
        }

        return hitAnything;
    }
}
