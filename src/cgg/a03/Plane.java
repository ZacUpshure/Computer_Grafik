package cgg.a03;

import cgtools.Vec3;

public class Plane implements Shape {

    private Vec3 p;
    private Vec3 n;
    private Material material;

    Plane(Vec3 p, Vec3 n, Material material) {
        this.p = p;
        this.n = n;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        double t = Vec3.dotProduct(Vec3.subtract(p, r.x0), n) / Vec3.dotProduct(r.d, n);

        if (t > r.t0 && t < r.t1) return new Hit(t, r.pointAt(t), n, material);

        return null;
    }
}
