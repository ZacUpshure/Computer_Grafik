package cgg.a04;

import cgtools.Direction;
import cgtools.Vector;

public class Plane implements Shape {

    private Direction p;
    private Direction n;
    private Material material;

    Plane(Direction p, Direction n, Material material) {
        this.p = p;
        this.n = n;
        this.material = material;
    }

    @Override
    public Hit intersect(Raytracer r) {
        double t = Vector.dotProduct(Vector.subtract(p, r.x0), n) / Vector.dotProduct(r.d, n);

        if (t > r.t0 && t < r.t1) return new Hit(t, r.pointAt(t), n, material);

        return null;
    }
}
