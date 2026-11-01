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
    public boolean hit(Ray r, Interval rayT, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = rayT.max();

        for (Hittable object : objects) {
            if (object.hit(r, new Interval(rayT.min(), closestSoFar), tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec.copyFrom(tempRec);
            }
        }

        return hitAnything;
    }
}
