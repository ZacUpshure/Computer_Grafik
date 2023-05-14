package cgg.a03;

import cgtools.Vec3;

public class Background implements Shape {

    private Material material;

    Background(Material material) {
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        return new Hit(r.t1, r.pointAt(r.t1), Vec3.vec3(1,0,0), material);
    }
}
